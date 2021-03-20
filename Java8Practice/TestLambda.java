import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestLambda {

	@FunctionalInterface
	public interface Bala{
		int add(int a,int b);
		//void print();
	}
	
	
	
	public static int totalValues(List<Integer> numbers, Predicate<Integer> selector) {
		return numbers.stream()
					  .filter(selector)
					  .reduce(0, (c,e)->c+e);
		
	}
	
	public static void main(String[] args) {
		
		TestLambda test = new TestLambda();
		Bala bala = (int a, int b) -> {
			System.out.println(a+b);
			return a+b;
		};
		bala.add(10,2);
		
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
/*		int total=0;
		for(int e:values) {
			total +=e*2;
		}
		System.out.println(total);
*/		values.stream().map(e -> e*2).reduce(0, (c,e)->c+e);
		
		System.out.println(totalValues(values,e -> true));
		System.out.println(totalValues(values,e -> e % 2 ==0));
		System.out.println(totalValues(values,e -> e % 2 !=0));
		
		System.out.println(
				values.stream()
					  .filter(e-> e > 3)
					  .filter(e -> e % 2 ==0)
					  .map(e -> e * 2)
					  .findFirst()
					  .orElse(0)
				);
	}

}
