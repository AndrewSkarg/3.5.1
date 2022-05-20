package com.exam;

import org.w3c.dom.ranges.Range;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        byte n = 6;
        byte m = 4;
        int[] mas1 = new int[n];
        int[] mas2 = new int[m];
        mas1 = fillMas(mas1);
        mas2 = fillMas(mas2);

        int[] sum = findSumOfArrays(mas1, mas2);
        System.out.println(Arrays.toString(mas1));
        System.out.println(Arrays.toString(mas2));
        System.out.println(Arrays.toString(sum));


    }


    public static int[] findSumOfArrays(int[] first, int[] second) {
        Stack<Integer> stack = new Stack();             //LIFO
        Queue<Integer> q = new LinkedList<>();         //FIFO
        if(second.length < first.length){
            int[]temp=first;
            first=second;
            second=temp;
        }
        int j=first.length-1;

        for (int i = second.length-1; i > -1; i--) {
            if (j == -1) {
                while (i > -1) {
                    if (!q.isEmpty()) {
                        int toPush = second[i] + q.poll();
                        if (toPush > 9) {
                            stack.push(toPush % 10);
                            toPush = toPush / 10 % 10;
                            q.offer(toPush);
                        } else {
                            stack.push(toPush);
                        }

                    } else {
                        stack.push(second[i]);
                    }
                    i--;
                }
                break;
            }
            int firstNum = second[i] + first[j];
            if (!q.isEmpty()) {
                firstNum += q.poll();
            }
            int secondNum = -1;
            int fN = firstNum;
            do {
                if (fN > 9) {
                    fN = firstNum / 10 % 10;      //FIFO QUEUE FIRST
                    secondNum = firstNum % 10;    //LIFO STACK SECOND
                }
                if (secondNum != -1) {
                    if (q.size() != 0) {
                        secondNum += q.poll();
                    } else {
                        q.offer(fN);
                        fN = 0;
                    }
                    stack.push(secondNum);
                } else if (secondNum == -1 && q.isEmpty()) {
                    stack.push(fN);
                    fN = 0;
                }
            } while (fN > 9);
            j--;
        }

        int[] sum = new int[stack.size()];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = stack.pop();
        }
        return sum;
    }


    public static int[] fillMas(int[] mas) {
        for (byte i = 0; i < mas.length; i++) {
            mas[i] = (byte) (Math.random() * 10);
        }
        return mas;
    }
}
