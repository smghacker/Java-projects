package com.vmware.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class CommandInterpreter {
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

	private static void process(InputStreamReader in) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(in);

		CommandInterpreter cmdInterpreter = new CommandInterpreter();
		try {
			String singleLine = bufferedReader.readLine();
			while (singleLine != null) {
				String result = cmdInterpreter.processLine(singleLine);
				System.out.println(result);
				singleLine = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}

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

		Command cmd = allCommands.get(command);
		if (cmd != null) {
			return cmd.execute(cmdArgs);
		} else {
			String error = "There is no such command " + command;
			return error;
		}
	}
	
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		process(in);
		in.close();
	}
}
