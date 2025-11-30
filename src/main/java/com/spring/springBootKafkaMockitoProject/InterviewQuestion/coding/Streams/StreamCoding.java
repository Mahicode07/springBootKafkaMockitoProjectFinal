//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.Streams;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCoding {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(12, 13, 12, 44, 5, 5, 14, 5, 6, 7, 812, 4, 5, 6);
        List<Integer> list = stream.distinct().toList();
        List<Integer> list1 = list.stream().filter((i) -> IntStream.rangeClosed(2, i / 2).noneMatch((d) -> i % d == 0)).toList();
        System.out.println(list1);
        List<String> l = Arrays.asList("10abc", "20def", "30ghi", "11jkl", "mno12", "pqr13");
        List<String> list2 = l.stream().filter((d) -> Character.isDigit(d.charAt(0))).toList();
        System.out.println(list2);
        List<String> list3 = l.stream().filter((d) -> Character.isDigit(d.charAt(d.length() - 1))).toList();
        System.out.println(list3);
        Stream var10000 = l.stream().filter((d) -> d.chars().anyMatch(Character::isDigit));
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
        String s = "madam";
        boolean b = IntStream.range(0, s.length() / 2).allMatch((d) -> s.charAt(d) == s.charAt(s.length() - 1 - d));
        System.out.println(b);
    }
}
