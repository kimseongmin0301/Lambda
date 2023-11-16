package lambdasinaction._01lambda.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import lambdasinaction._01lambda.basic1.Apple;

public class FunctionNegateAnd {
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple(150, "green"));
		inventory.add(new Apple(200, "green"));
		inventory.add(new Apple(200, "red"));
		inventory.add(new Apple(150, "red"));
		
		// Predicate 선언하기
		Predicate<Apple> redApple = a -> a.getColor().equals("red");
		// Predicate 뒤집기
		Predicate<Apple> notRedApple = redApple.negate();
		// red & weight > 150
		Predicate<Apple> redHeavyApple = redApple.and(a -> a.getWeight() > 150);

		System.out.println(filterApples2(inventory, notRedApple));
		System.out.println(filterApples2(inventory, redHeavyApple));

	}

	public static List<Apple> filterApples2(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
}
