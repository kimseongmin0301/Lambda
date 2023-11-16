package lambdasinaction._02stream.basic2;
import lambdasinaction._02stream.basic1.*;

import java.util.*;

import static lambdasinaction._02stream.basic1.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream()
                .anyMatch(Dish::isVegetarian); // dish -> dish.isVegetarian 하나라도 일치하는지
    }
    //2.allMatch
    private static boolean isHealthyMenu(){

        return menu.stream()
                .allMatch(dish -> dish.getCalories() <= 800); // 모두 일치하는지
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){

        return menu.stream()
                .noneMatch(dish -> dish.getCalories() > 800); // 모두 일치하지않는지
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){

        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
    
}
