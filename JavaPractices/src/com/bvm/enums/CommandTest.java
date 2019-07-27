package com.bvm.enums;

public class CommandTest {
	public static void main(String... args) {
		// Execute all commands in the command list
		for (Command cmd : CommandList.values()) {
			cmd.execute();
		}
	}
}

interface Command {
	void execute();
}

enum CommandList implements Command {
	RUN {
		public void execute() {
			System.out.println("Running...");
		}
	},
	JUMP {
		public void execute() {
			System.out.println("Jumping...");
		}
	};
	// Force all constants to implement the execute() method.
	public abstract void execute();
}
