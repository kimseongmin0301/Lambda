package lambdasinaction._02stream.collect;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction._02stream.collect.Dish.menu;

public class _02Summarizing {

    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    //1. Comparator 를 사용한 collect(), reducing()
    private static Dish findMostCaloricDishUsingComparator() {

        return menu.stream()
                .collect(maxBy(Comparator.comparingInt(Dish::getCalories)))
                .get();
    }

    //collect() - reducing 사용
    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    //2. summingInt() 사용
    private static int calculateTotalCalories() {

        return menu.stream()
                .collect(summingInt(Dish::getCalories));
    }

    //3. averagingInt() 사용
    private static Double calculateAverageCalories() {

        return menu.stream()
                .collect(summingDouble(Dish::getCalories));
    }

    //4. summarizingInt() 사용
    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream()
                .collect(summarizingInt(Dish::getCalories));
    }

    //5. joining() 사용
    private static String getShortMenu() {

        return menu.stream()
                .map(Dish::getName)
                .collect(joining(" "));
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
