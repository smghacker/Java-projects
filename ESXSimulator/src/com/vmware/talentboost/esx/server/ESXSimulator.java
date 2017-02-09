package com.vmware.talentboost.esx.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.AddDeviceCommand;
import com.vmware.talentboost.esx.commands.CreateVMCommand;
import com.vmware.talentboost.esx.commands.DeleteDeviceCommand;
import com.vmware.talentboost.esx.commands.DeleteVMCommand;
import com.vmware.talentboost.esx.commands.EditVMCommand;
import com.vmware.talentboost.esx.commands.HelpCommand;
import com.vmware.talentboost.esx.commands.ICommand;
import com.vmware.talentboost.esx.commands.PrintVMsCommand;
import com.vmware.talentboost.esx.commands.ReadFromFilesCommand;
import com.vmware.talentboost.esx.commands.ReadVMCommand;
import com.vmware.talentboost.esx.commands.SaveVMCommand;
import com.vmware.talentboost.esx.commands.StopAllVMCommand;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.managers.VirtualMachineManager;
import com.vmware.talentboost.esx.storages.ILocalVMStorage;
import com.vmware.talentboost.esx.storages.IVMStorage;
import com.vmware.talentboost.esx.storages.LocalVMStorageWithTextFiles;
import com.vmware.talentboost.esx.storages.VMStorageWithHashMap;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * 
 * This class holds the whole logic for the ESX simulator. It is the consuming
 * part of the concurrent Producer-Consumer pattern.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class ESXSimulator implements Runnable {
	private static final String FOLDER = "FilesWithCommands";
	private static final String EXTENSION = "txt";
	private static final int NUM_OF_MAX_ALLOWED_THREADS = 4;

	private IVMStorage storage = new VMStorageWithHashMap();
	private ILocalVMStorage localStorage = new LocalVMStorageWithTextFiles();
	private IVirtualMachineManager virtualMachineManager = new VirtualMachineManager(
			storage, localStorage);

	private BlockingQueue<String> requestsQueue;
	private CommandProducer systemInProducer = new CommandProducer(System.in,
			requestsQueue);

	private volatile boolean stateOn;
	ExecutorService threadPool = Executors
			.newFixedThreadPool(NUM_OF_MAX_ALLOWED_THREADS);

	private final Collection<ICommand> Commands = Arrays.asList(new ICommand[] {
			new AddDeviceCommand(virtualMachineManager),
			new CreateVMCommand(virtualMachineManager),
			new DeleteDeviceCommand(virtualMachineManager),
			new DeleteVMCommand(virtualMachineManager),
			new DeleteVMCommand(virtualMachineManager),
			new EditVMCommand(virtualMachineManager),
			new PrintVMsCommand(virtualMachineManager), new HelpCommand(),
			new SaveVMCommand(virtualMachineManager),
			new ReadVMCommand(virtualMachineManager),
			new ReadFromFilesCommand(this), new StopAllVMCommand(this) });

	private final Map<String, ICommand> allCommands = new HashMap<String, ICommand>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -50857985995668357L;
		{
			for (ICommand cmd : Commands) {
				put(cmd.getCommandName(), cmd);
			}
		}
	};

	private void loadAllVms() {
		String fileNameWithAllVMs = localStorage.getFileNameWithAllVms();
		String path = localStorage.constructPath(fileNameWithAllVMs);
		File newFile = new File(path);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(newFile));
			String singleLine = "";
			singleLine = bufferedReader.readLine();
			while (singleLine != null) {
				new ReadVMCommand(virtualMachineManager).execute(singleLine);
				singleLine = bufferedReader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NumberOfArgumentsException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Class constructor specifying the queue where the requests will be stored
	 * 
	 * @param requestsQueue
	 */
	public ESXSimulator(BlockingQueue<String> requestsQueue) {
		this.requestsQueue = requestsQueue;
	}

	public BlockingQueue<String> getRequestsQueue() {
		return requestsQueue;
	}

	public boolean isStateOn() {
		return stateOn;
	}

	public void setStateOn(boolean stateOn) {
		this.stateOn = stateOn;
	}

	/**
	 * Processes a single line in the following way: 1. Slit 2.Extract command
	 * arguments 3.Execute command
	 * 
	 * @param line
	 * @return command response(OK status or some exception message)
	 */
	public String processLine(String line) {

		String[] allWords = line.split("\\s+");
		String command = allWords[0];
		String cmdArgs = "";
		for (int i = 1; i < allWords.length; i++) {
			if (allWords[i] != " ") {
				cmdArgs += allWords[i];
				if (i != allWords.length - 1) {
					cmdArgs += " ";
				}
			}
		}

		ICommand cmd = allCommands.get(command);
		if (cmd != null) {
			try {
				String result = cmd.execute(cmdArgs);
				return result;
			} catch (NumberOfArgumentsException e) {
				return e.getMessage();
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
		} else {
			String error = "There is no such command " + command;
			return error;
		}
	}

	/**
	 * Submits tasks to thread pool, where each task is reading from file and
	 * producing a command
	 * 
	 * @param arguments
	 *            array of strings with file names
	 * @throws FileNotFoundException
	 */
	public void startReadingFromFiles(String[] arguments)
			throws FileNotFoundException {
		for (int i = 0; i < arguments.length; i++) {
			threadPool.submit(new CommandProducer(FOLDER + "/" + arguments[i]
					+ "." + EXTENSION, requestsQueue));
		}

	}

	/**
	 * Saves all currently loaded virtual machines locally(in text files) and
	 * then set the state of ESX simulator to off. Shutdowns the thread pool
	 * 
	 * @throws IllegalArgumentException
	 */
	public void shutDown() throws IllegalArgumentException {
		setStateOn(false);
		Collection<IVirtualMachine> allVMs = virtualMachineManager
				.getAllExistingVMs();
		for (IVirtualMachine vm : allVMs) {
			try {
				virtualMachineManager.saveVM(vm.getId());

			} catch (VMNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IOException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}

		threadPool.shutdown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		loadAllVms();
		new Thread(systemInProducer).start();
		stateOn = true;
		while (true && stateOn) {
			try {
				String line = requestsQueue.take();
				String result = processLine(line);
				System.out.println(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
