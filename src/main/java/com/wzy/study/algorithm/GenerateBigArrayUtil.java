package com.wzy.study.algorithm;

import com.alibaba.fastjson.JSON;
import com.wzy.study.algorithm.model.StabilityObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * 大数组生成工具
 *
 * @author 王志英
 */
@Slf4j
public class GenerateBigArrayUtil {

    /**
     * 获取随机数组存入文件
     *
     * @param count
     */
    public static void getArray(int count) {
        File f = new File("D:/out/temp/");
        if (!f.exists()) {
            f.mkdirs();
        }
        try (Writer writer = new FileWriter("D:/out/temp/array.txt")) {
            int x = new Random().nextInt(10000 * 10000);
            writer.write("" + x);
            for (int i = 0; i < count; i++) {
                x = new Random().nextInt(10000 * 10000);
                writer.write(("," + x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取随机数组存入文件
     *
     * @param count
     */
    public static void getArrayAllowRepeat(int count) {
        File f = new File("D:/out/temp/");
        if (!f.exists()) {
            f.mkdirs();
        }
        try (Writer writer = new FileWriter("D:/out/temp/arrayAllowRepeat.txt")) {
            int x = (int) (Math.random() * 1000);
            writer.write(x);
            for (int i = 0; i < count; i++) {
                x = (int) (Math.random() * 1000);
                writer.write(("," + x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取数组
     *
     * @return
     * @throws Exception
     */
    public static Integer[] readArray() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/out/temp/array.txt"))) {
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            String[] ss = sb.toString().split(",");
            Integer[] ret = new Integer[ss.length];
            for (int i = 0; i < ss.length; i++) {
                ret[i] = Integer.valueOf(ss[i]);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * 判断数组是否有序
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean checkOrder(T[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i].compareTo(target[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
//        getArray(10000 * 10);
//        Integer[] array = readArray();
//        charu(array);
//        maopao(array);
//        Collections.sort(array);
//        System.err.println(line(array, 36194523));
//        System.err.println(binarySearch(array, 36194523));
//        maopao(array);
//        maopao(array);

//        StabilityObject[] data = StabilityObject.getTestArray();
//        log.info("排序前数组：{}", JSON.toJSONString(data));
//        inMethod(1);
//        charu(data);
//        log.info("插入排序后数组：{}", JSON.toJSONString(data));
//        methodToMethod(3);

//        StabilityObject[] data = StabilityObject.getTestArray(10);
//        log.info("排序前数组：{}", JSON.toJSONString(data));
//        log.info("排序前是否有序{}", checkOrder(data));
//        inMethod(1);
//        new MySortAlgorithm.Xuanze().sort(data);
//        log.info("选择排序后数组：{}", JSON.toJSONString(data));
//        log.info("选择排序后是否有序{}", checkOrder(data));
//        methodToMethod(1);

        StabilityObject[] data = StabilityObject.getTestArray(10);
        log.info("排序前数组：{}", JSON.toJSONString(data));
        log.info("排序前是否有序{}", checkOrder(data));
        inMethod(1);
        new MySortAlgorithm.Kuaisu().sort(data);
        log.info("快速排序后数组：{}", JSON.toJSONString(data));
        log.info("快速排序后是否有序{}", checkOrder(data));
        methodToMethod(1);
    }

    /**
     * 二分查找
     * log(n),对数执行，时间极短
     *
     * @param source
     * @param target
     * @return
     */
    public static int binarySearch(int[] source, int target) {
        int low = 0, high = source.length - 1;
        long t1 = System.currentTimeMillis();
        int a = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (source[mid] < target) {
                low = mid + 1;
            } else if (source[mid] > target) {
                high = mid - 1;
            } else {
                a = mid;
                break;
            }
        }
        long t2 = System.currentTimeMillis();
        System.err.println("折半查找时间" + (t2 - t1));
        return a;
    }

    /**
     * 顺序查找
     * 线性执行，时间较短
     *
     * @param source
     * @param target
     * @return
     */
    public static int line(int[] source, int target) {
        long t1 = System.currentTimeMillis();
        int a = -1;
        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                a = i;
            }
        }
        long t2 = System.currentTimeMillis();
        System.err.println("顺序查找执行时间" + (t2 - t1));
        return a;
    }

    public static void methodToMethod(int count) {
        for (int i = 0; i < count; i++) {
            log.info("********************************************");
        }
    }

    public static void inMethod(int count) {
        for (int i = 0; i < count; i++) {
            log.info("---------------------------------------");
        }
    }
}
