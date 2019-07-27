package com.bvm.interview;

public interface InterfaceJava8 {

	public static int test = 100;

	default void testMethod() {
		System.out.println("in default testMethod");
	};

	abstract void testMethod1();

	void testMethod2();

	public interface Job {
		// A nested class
		class EmptyJob implements Job {
			private EmptyJob() {
				// Do not allow outside to create its object
			}

			public void runJob() {
				System.out.println("Nothing serious to run...");
			}
		}

		// A constant field
		Job EMPTY_JOB = new EmptyJob();

		// An abstract method
		void runJob();
	}
}
