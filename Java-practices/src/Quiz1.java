
public class Quiz1 {

	public static void main(String[] args) {
		Account account = new Account();
		account.withdraw(100);

		Checking checking = new Checking();
		checking.withdraw(200);

		Savings savings = new Savings();
		savings.withdraw(300);

		account.staticOverride();
		checking.staticOverride();
		savings.staticOverride();

		account.methodFromAccount();
		checking.methodFromAccount();
		savings.methodFromAccount();
		
		//((Checking)acc1).methodFromChecking(); // runtime classcast exception
		((Checking)checking).methodFromChecking();
		//((Checking)acc3).methodFromChecking(); // runtime classcast exception

		//((Savings)acc1).methodFromSavings(); // runtime classcast exception
		//((Savings)acc2).methodFromSavings(); // runtime classcast exception
		((Savings)savings).methodFromSavings();

		account = checking;
		account.withdraw(100);
		checking.withdraw(100);
		savings.withdraw(100);
		
		if(account instanceof Checking) {
			System.out.println("block will run");
		}

		account = savings;
		account.withdraw(200);
		checking.withdraw(200);
		savings.withdraw(200);
		
//		checking = savings;
//		account.withdraw(300);
//		checking.withdraw(300);
//		savings.withdraw(300);
		
		savings=(Savings) account;
		account.withdraw(400);
	}
}

class Account {
	public void withdraw(double money) {
		System.out.println("withdrawn from account: " + money);
	}

	public static void staticOverride() {
		System.out.println("account staticOverride: ");
	}

	public void methodFromAccount() {
		System.out.println("method from account ");
	}
}

class Checking extends Account {
	public void withdraw(double money) {
		System.out.println("withdrawn from checking: " + money);
	}

	public void methodFromChecking() {
		System.out.println("method from checking ");
	}
}

class Savings extends Account {
	public void withdraw(double money) {
		System.out.println("withdrawn from savings: " + money);
	}

	public void methodFromSavings() {
		System.out.println("method from savings ");
	}
}
