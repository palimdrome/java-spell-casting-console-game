package com.limuelle;

/**
 * The invoker class that contains the methods that can hold and execute a command as an object.
 * @author Limuelle Alamil
 *
 */

public class RemoteControl {
	private Command command;

	/**
	 * Holds/Stores the command in the "remote control".
	 * @param command The command as an object.
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	
	/**
	 * Executes the command by "pressing the remote control".
	 */
	public void pressButton() {
		command.execute();
	}
}
