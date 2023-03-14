package Programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/150370?language=java
개인 정보 수집 유효 기간
 */
public class PrivacyPolicy {

    private static int[] getResult(String today, String[] terms, String[] privacies) {

        final int size = privacies.length;
        final LocalDate todayLocalDate = parseLocalDate(today);

        // get terms as map
        Map<String, Long> termMap = new HashMap<>();

        for (String term : terms) {
            String[] termArr = term.split(" ");
            termMap.put(termArr[0], Long.parseLong(termArr[1]));
        }

        // get privacy as array
        String[][] privacyArray = new String[size][2];

        for (int i = 0; i < size; i++) {
            String[] privArr = privacies[i].split(" ");
            privacyArray[i][0] = privArr[1];
            privacyArray[i][1] = privArr[0];
        }

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String type = privacyArray[i][0];
            LocalDate endDate = parseLocalDate(privacyArray[i][1]).plusMonths(termMap.get(type));

            if (todayLocalDate.isAfter(endDate.minusDays(1))) {
                resultList.add(i + 1);
            }
        }

        return resultList.stream()
                         .mapToInt(i -> i)
                         .toArray();

    }

    private static LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString.replace(".", "-"), DateTimeFormatter.ISO_DATE);
    }

    public static void main(String[] args) {

        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        System.out.println(Arrays.toString(getResult(today, terms, privacies)));
    }

}
