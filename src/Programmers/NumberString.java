package Programmers;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/81301
숫자 문자열과 영단어
 */
public class NumberString {

    private static int getResult(String s) {
        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine"};
        for (int i = 0; i < arr.length; i++) {
            s = s.replace(arr[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        String s = "one4seveneight";

        System.out.println(getResult(s));
    }
}
