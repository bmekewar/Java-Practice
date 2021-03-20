package defog;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {

	private static ExecutorService service = Executors.newFixedThreadPool(4);
	// private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			int id = i;
			service.submit(() -> {
				String birthDate = new UserService().birthDate(1);
				System.out.println("date" + id + ":" + birthDate);
			});
		}
		Thread.sleep(10000);
		service.shutdown();
	}

	public String birthDate(int userId) {
		// Date birthDate = birthDateFromDB(userId);
		Date date = new Date(System.currentTimeMillis());
		//final SimpleDateFormat format = ThreadLocalFormatter.dateFormatter.get();
		ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));
		SimpleDateFormat format = dateFormatter.get();
		dateFormatter.remove();
		return format.format(date);
	}

	static class ThreadLocalFormatter {

		//public static ThreadLocal<SimpleDateFormat> dateFormatter  = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));
		public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {

			@Override
			protected SimpleDateFormat initialValue() {
				return new SimpleDateFormat("dd-MM-yyyy");
			}

			@Override
			public SimpleDateFormat get() {
				return super.get();
			}

		};
	}

}
