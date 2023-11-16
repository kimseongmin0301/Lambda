package lambdasinaction._02stream.basic1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
        System.out.println("---");

        System.out.println(getGroupingMenu(Dish.menu));
        System.out.println("---");

        System.out.println(getMaxCaloryDish(Dish.menu));
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                // filter(Predicate)
                .filter(dish -> dish.getCalories() <= 400)
                // sorted(Comparator)
                .sorted(Comparator.comparing(Dish::getCalories))
                // map(Function)
                .map(Dish::getName)
                // collect(Collector)
                .collect(toList())
                .subList(0, 3);
    }

    //400칼로리 이하인 메뉴를 diet, 아닐 경우 normal 그룹핑해라.
    public static Map<String, List<Dish>> getGroupingMenu(List<Dish> dishes){

        return dishes.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return "diet";
                    else return "normal";
                }));
    }


    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {

//        return dishes.stream()
//                .max(Comparator.comparing(dish -> dish.getCalories()))
//                .get();

//        Optional<Dish> optionalDish = dishes.stream()
//                .max(Comparator.comparing(Dish::getCalories));
//        Dish dish = null;
//        if(optionalDish.isPresent()){
//           dish = optionalDish.get();
//        }
//
//        return dish;

//        return dishes.stream()
//                .max(Comparator.comparing(Dish::getCalories))
//                .get();
        return dishes.stream()
                .max(Comparator.comparing(Dish::getCalories))
//                .orElse(new Dish());
                .orElseGet(Dish::new); // () -> new Dish()
    }
}
