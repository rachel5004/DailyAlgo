package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
https://school.programmers.co.kr/learn/courses/30/lessons/92334
신고 결과 받
 */
public class ReportResult {

    private static int[] getResult(String[] id_list, String[] report, int k) {
        // 리폿당한 횟수
        Map<String, Integer> reportCnt = Arrays.stream(id_list)
            .collect(Collectors.toMap(id -> id, id -> 0,
                (u, v) -> null,
                LinkedHashMap::new
            ));

        Map<String, List<String>> reporter = Arrays.stream(id_list)
            .collect(Collectors.toMap(id -> id, id -> new ArrayList<>(),
                (u, v) -> null,
                LinkedHashMap::new
            ));

        for (String s : report) {
            String[] reportStr = s.split(" ");
            reporter.get(reportStr[1]).add(reportStr[0]);  // 나를 신고한 사람 목록
        }

        reporter.forEach((key, value) -> {
            value = value.stream().distinct().collect(Collectors.toList());
            if (value.size() >= k) {
                value.forEach(it -> reportCnt.put(it, reportCnt.get(it) + 1));
            }
        });
        return reportCnt.values()
            .stream()
            .mapToInt(i -> i)
            .toArray();

    }

    public static void main(String[] args) {

        String[] ids = {"con", "ryan"};
        String[] reports = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 3;
        System.out.println(Arrays.toString(getResult(ids, reports, k)));
    }

}
