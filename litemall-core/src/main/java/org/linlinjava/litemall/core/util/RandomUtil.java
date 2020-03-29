package org.linlinjava.litemall.core.util;

import java.util.List;

public class RandomUtil {


    /**
     * 按照概率，获取落在哪一块
     */
    public static int choose(List<Integer> ranges) {
        int total = 0;

        for (Integer i : ranges) {
            total += i;
        }

        double random = Math.random();

        double r = random * total;

        int temp = 0;
        for (Integer i : ranges) {
            temp += i;
            if (r < temp) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 随机摇摆
     */
    public static double wave(double begin, double end) {
        double r = Math.random();

        return begin + (end - begin) * r;
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(wave(100, 1000));
//        }

//        int result = 0;
//        Map<Integer, Integer> count = new HashMap<>();
//        StopWatch sw = new StopWatch("1");
//        sw.start();
//        for (int i = 0; i < 1000; i++) {
//
//            result = RandomUtil.choose(50, 40, 10);
//
//            if (count.get(result) == null) {
//                count.put(result, 1);
//            } else {
//                count.put(result, count.get(result) + 1);
//            }
//        }
//        sw.stop();
//        System.out.println(count);
//        System.out.println(sw);
    }
}
