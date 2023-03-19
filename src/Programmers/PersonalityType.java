package Programmers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/118666
성격 유형 검사하기
 */
public class PersonalityType {

    private static String getResult(String[] survey, int[] choices) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Arrays.stream("RTCFJMAN".split(""))
            .forEach(type -> map.put(type, 0));

        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            if (choice == 4) continue;
            if (choice < 4) {
                map.merge(survey[i].split("")[0], Math.abs(choice - 4), Integer::sum);
            } else {
                map.merge(survey[i].split("")[1], Math.abs(choice - 4), Integer::sum);
            }
        }

        return IntStream.range(0, map.size() / 2)
            .mapToObj(i -> {
                String key1 = (String) map.keySet().toArray()[i * 2];
                String key2 = (String) map.keySet().toArray()[(i * 2) + 1];
                return map.get(key1) >= map.get(key2) ? key1 : key2;
            })
            .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

        System.out.println(getResult(survey, choices));
    }

}
