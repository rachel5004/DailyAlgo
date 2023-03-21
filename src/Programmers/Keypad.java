package Programmers;

import java.util.List;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/67256
키패드 누르기
 */
public class Keypad {

    private static String getResult(int[] numbers, String hand) {
        List<Integer> left = List.of(1, 4, 7);
        List<Integer> right = List.of(3, 6, 9);

        int lhand = 10;
        int rhand = 12;
        StringBuilder result = new StringBuilder();

        for (int n : numbers) {
            if (left.contains(n)) {
                result.append("L");
                lhand = n;
            } else if (right.contains(n)) {
                result.append("R");
                rhand = n;
            } else {
                n = n == 0 ? 11 : n;
                int[] l = getMatrix(lhand);
                int[] r = getMatrix(rhand);
                int[] an = getMatrix(n);

               if(getDistance(l,an) < getDistance(r,an)){
                   result.append("L");
                   lhand = n;
               }else if(getDistance(l,an) > getDistance(r,an)){
                   result.append("R");
                   rhand = n;
               }else {
                   if("right".equals(hand)) {
                       result.append("R");
                       rhand = n;
                   }else {
                       result.append("L");
                       lhand = n;
                   }
               }
            }
        }
        return result.toString();
    }

    private static int[] getMatrix(int num) {
        return new int[]{(num - 1) % 3, (num - 1) / 3};
    }
    private static int getDistance(int[] num1,int[] num2) {
        return Math.abs(num1[0]-num2[0]) + Math.abs(num1[1]-num2[1]);
    }

    public static void main(String[] args) {
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";

        System.out.println(getResult(numbers, hand));
    }
}
