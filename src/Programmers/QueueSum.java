package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/118667
두 큐 합 같게 만들기
 */
public class QueueSum {

    private static int getResult(int[] queue1, int[] queue2) {

        Queue<Integer> que1 = new LinkedList<>();
        Arrays.stream(queue1).forEach(que1::add);

        Queue<Integer> que2 = new LinkedList<>();
        Arrays.stream(queue2).forEach(que2::add);

        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();

        int count = 0;
        while(sum1 != sum2) {
            if(sum1 > sum2) {
                int value = que1.poll();
                sum1 -= value;
                sum2 += value;
                que2.add(value);
            } else {
                int value = que2.poll();
                sum1 += value;
                sum2 -= value;
                que1.add(value);
            }
            count++;
            if(count > (queue1.length * 4)) return -1;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] q1 = {1, 2, 1, 2};
        int[] q2 = {1, 10, 1, 2};

        System.out.println(getResult(q1, q2));
    }

}
