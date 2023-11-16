package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lambdasinaction._02stream.basic1.Dish;
import static lambdasinaction._02stream.basic1.Dish.menu;


public class _05Reducing {

    public static void main(String... args) {

        //reduce - a + b 연산
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        // 1. reduce() 메서드 구현 - 합계 구하기
        int sum = numbers.stream()
//                .reduce(0, (n1, n2) -> n1 + n2);
                .reduce(0, Integer::sum);
        System.out.println("sum = " + sum);

        // 2. Stream의 mapToInt() 함수로 생성한 IntStream의 sum() 사용 - 합계 구하기
        sum = numbers.stream()
                // mapToInt(ToIntFunction) ToIntFunction의 추상메서드 int applyAsInt(T value)
                .mapToInt(Integer::intValue) // IntStream
                .sum();
        System.out.println("mapToInt() IntStream sum = " + sum);

        // 3. Stream의 flatMapToInt() 함수로 생성한 IntStream의 sum() 사용 - 합계 구하기
        sum = numbers.stream()
                .flatMapToInt(IntStream::of)
                .sum();
        System.out.println("flatMapToInt() IntStream sum = " + sum);

        // 1. Stream의 reducer() 메서드 구현 - reduce - 최대값 구하기
        int max = numbers.stream()
                .reduce(0, Integer::max);
        System.out.println("max = " + max);
        // 2. Stream의 reducer() 메서드 사용 - reduce - 최대값 구하기
        max = numbers.stream()
                // max(Comparator)
                .max(Integer::compareTo) // Optional<Integer>
//                .get();
                .orElse(0);
        System.out.println("Stream max = " + max);


        // reduce - 최소값 구하기
        int min = numbers.stream()
                .reduce(Integer::min)
                .orElse(0);
        System.out.println("min = " + min);


        Optional<Integer> optional = numbers.stream().reduce(Integer::min);
        optional.ifPresent(System.out::println);

        //Dish 의  총 칼로리 합계를 구하는 여러가지 방법
        //1. reduce() 함수로 구현
        Integer totalValue = menu.stream() //Stream<Dish>
                .map(Dish::getCalories) //Stream<Integer>
                .reduce(0, (dish1, dish2) -> dish1 + dish2);
        System.out.println("totalValue = " + totalValue);

        //2. reduce() 함수에서 Integer.sum() 메서드 호출
        totalValue = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum) //Optional<Integer>
                .orElse(0);
        System.out.println("totalValue2 = " + totalValue);

        //3. mapToInt()사용하여 IntStream 변환하여 sum() 호출
        totalValue = menu.stream()
                // .mapToInt(dish -> dish.getCalories())
                .mapToInt(Dish::getCalories) //IntStream
                .sum();
        System.out.println("totalValue3 = " + totalValue);

        //4. Collectors의 summingInt() 호출
        totalValue = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalValue4 = " + totalValue);

        IntSummaryStatistics statistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("statistics = " + statistics);



    }
}
