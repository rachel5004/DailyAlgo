package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/150368
이모티콘 할인행사
 */
public class EmoticonSale {

    private static int[] getResult(int[][] users, int[] emoticons) {
        // 가능한 모든 세일 경우의 수
        List<Integer> sales = List.of(10, 20, 30, 40);
        List<int[]> cases = combinationsWithDuplicate(sales, emoticons.length);

        List<int[]> res = new ArrayList<>();

        for (int[] c : cases) {
            int sub = 0;
            int inc = 0;
            for (int[] user : users) {
                int ratio = user[0];  // 최소 할인 비율
                int subscribe = user[1];  // 가입 전환액

                // 할인율이 부합하는 이모티콘 구매
                int purchase = 0;
                for (int j = 0; j < c.length; j++) {
                    if (c[j] >= ratio) purchase += emoticons[j] * (100 - c[j]) / 100;
                }

                // 구매액이 가입 전환액보다 크다면 가입
                if (purchase >= subscribe)  sub++;
                else inc += purchase;

            }
            res.add(new int[]{sub, inc});
        }

        return res.stream()
            .sorted(Comparator.comparingInt((int[] i) -> i[1]).reversed())
            .sorted(Comparator.comparingInt((int[] i) -> i[0]).reversed())
            .findFirst().get();
    }

    public static List<int[]> combinationsWithDuplicate(List<Integer> list, int n) {
        return IntStream.range(0, (int) Math.pow(list.size(), n))
            .mapToObj(i -> {
                int[] combination = new int[n];
                for (int j = 0; j < n; j++) {
                    combination[j] = list.get((i / (int) Math.pow(list.size(), j)) % list.size());
                }
                return combination;
            })
            .distinct()
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200},
            {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        System.out.println(Arrays.toString(getResult(users, emoticons)));
    }
}
