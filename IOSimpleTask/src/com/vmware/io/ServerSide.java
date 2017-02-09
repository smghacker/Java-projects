package com.vmware.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.vmware.io.CalcCommand;
import com.vmware.io.CountWordsCommand;
import com.vmware.io.GetCommand;
import com.vmware.io.LoadTableCommand;
import com.vmware.io.ReverseStringCommand;
import com.vmware.io.ReverseWordsCommand;
import com.vmware.io.SelectFromCommand;
import com.vmware.io.SelectWhereFromCommand;
import com.vmware.io.SetCommand;
import com.vmware.io.Storage;
import com.vmware.io.VariableStorage;

public class ServerSide {
	
	private Storage database = new Storage();
	private VariableStorage variableStorage = new VariableStorage();
	private final Collection<Command> Commands = Arrays.asList(new Command[] {
			new ReverseStringCommand(), new ReverseWordsCommand(),
			new CountWordsCommand(), new LoadTableCommand(this.database),
			new SelectFromCommand(this.database),
			new SelectWhereFromCommand(this.database),
			new SetCommand(this.variableStorage),
			new GetCommand(this.variableStorage),
			new CalcCommand(this.variableStorage) });

	private final Map<String, Command> allCommands = new HashMap<String, Command>() {
		{
			for (Command cmd : Commands) {
				put(cmd.getCommandName(), cmd);
			}
		}
	};
	
	private int portNumber = 4443;

	private String processLine(String line){

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

		Command cmd = allCommands.get(command);
		if (cmd != null) {
			return cmd.execute(cmdArgs);
		} else {
			String error = "There is no such command " + command;
			return error;
		}
	}
	
	private void listen(){
		try (ServerSocket serverSocket =
                new ServerSocket(this.portNumber);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ){
			String inputLine = in.readLine();
			while(inputLine != null){
				String result = processLine(inputLine);
				out.println(result);
				inputLine = in.readLine();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ServerSide ss = new ServerSide();
		ss.listen();

	}

}
