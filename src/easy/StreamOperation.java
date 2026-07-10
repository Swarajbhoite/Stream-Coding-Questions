package easy;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamOperation {
    //    Return Duplicate  Elements
    public static <T> Set<T> duplicateElements(T[] t) {
        Set<T> set = new HashSet<>();
        Set<T> dupEle = Arrays.stream(t).filter(e -> !set.add(e)).collect(Collectors.toSet());
        return dupEle;
    }

    //    Return Non-Dup
    public static <T> Set<T> nonDuplicateElements(T[] t) {
        Set<T> set = new HashSet<>();
        Set<T> nonDupElememts = Arrays.stream(t).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toSet());
        return nonDupElememts;

    }


    public static void main(String[] args) {
        System.out.println("Duplicate Elements");
        Integer[] nos = {1, 2, 3, 4, 5, 6, 4, 7, 8, 9, 3, 5, 9};
        System.out.println(StreamOperation.duplicateElements(nos));
        Double[] prices = {10.5, 20.3, 30.5, 10.5, 45.7, 20.3, 60.1};
        System.out.println("Double Duplicates  : " + duplicateElements(prices));

        // String
        String[] names = {"Java", "Python", "Java", "C++", "Spring", "Python", "Docker"};
        System.out.println("String Duplicates  : " + duplicateElements(names));

        // Character
        Character[] letters = {'A', 'B', 'C', 'A', 'D', 'E', 'B', 'F'};
        System.out.println("Character Duplicates : " + duplicateElements(letters));
        System.out.println("Non - Duplicate Elements");
        System.out.println(nonDuplicateElements(nos));

        String concat = Arrays.stream(names).collect(Collectors.joining("||"));
        System.out.println(concat);


        List<List<String>> cities = List.of(

                List.of("Pune", "Mumbai"),
                List.of("Bhopal", "Indore")


        );
        Set<String> allcities = cities.stream().flatMap(Collection::stream).collect(Collectors.toSet());
        System.out.println(allcities);

        List<String> strings = Arrays.asList("", "", " ", "    ", "Hello", "World", "");
        Optional<String> firstNonEmpty = strings.stream().filter(s -> !s.isBlank()).findFirst();
        firstNonEmpty.ifPresent(System.out::println);
    }


}


