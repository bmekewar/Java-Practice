import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TimeSlot {
	
	static Random random = new Random();
	
	public boolean isAvailable() {
		//...
		return random.nextBoolean();
	}
	
	public void schedule() {
		//...
	}
	

	public static void main(String[] args) {
		List<TimeSlot> timeSlots = Arrays.asList(new TimeSlot(), new TimeSlot(), new TimeSlot(), new TimeSlot(),
				new TimeSlot());
		
		TimeSlot firstValue = timeSlots.stream()
				 .filter(TimeSlot::isAvailable)
				 .peek(TimeSlot::schedule)
				 .findFirst()
				 .orElse(new TimeSlot());
		
		System.out.println("firstValue:"+firstValue);
	}

}
