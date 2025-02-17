package com.bvm.enums;

public enum SuperSmartSeverity {
	LOW("Low Priority", 30) {
		public double getProjectedCost() {
			return 1000.0;
		}
	},
	MEDIUM("Medium Priority", 15) {
		public double getProjectedCost() {
			return 2000.0;
		}
	},
	HIGH("High Priority", 7) {
		public double getProjectedCost() {
			return 3000.0;
		}
	},
	URGENT("Urgent Priority", 1) {
		public double getProjectedCost() {
			return 5000.0;
		}
	};
	// Declare instance variables
	private String description;
	private int projectedTurnaroundDays;

	// Declare a private constructor
	private SuperSmartSeverity(String description, int projectedTurnaroundDays) {
		this.description = description;
		this.projectedTurnaroundDays = projectedTurnaroundDays;
	}

	// Declare a public method to get the turn around days
	public int getProjectedTurnaroundDays() {
		return projectedTurnaroundDays;
	}

	// Override the toString() method in the Enum class to return description
	@Override
	public String toString() {
		return this.description;
	}

	// Provide getProjectedCost() abstract method, so all constants
	// override and provide implementation for it in their body
	public abstract double getProjectedCost();

	public static void main(String[] args) {
		for (SuperSmartSeverity s : SuperSmartSeverity.values()) {
			String name = s.name();
			String desc = s.toString();
			int ordinal = s.ordinal();
			int projectedTurnaroundDays = s.getProjectedTurnaroundDays();
			double projectedCost = s.getProjectedCost();
			System.out.println("name=" + name + ", description=" + desc
					+ ", ordinal=" + ordinal + ", turnaround days="
					+ projectedTurnaroundDays + ", projected cost="
					+ projectedCost);
		}
	}
}
