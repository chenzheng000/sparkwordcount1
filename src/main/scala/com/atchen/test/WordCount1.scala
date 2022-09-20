package com.atchen.test

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import java.util.Arrays;


/**
 * @Author yqq
 * @Date 2021/12/06 23:16
 * @Version 1.0
 */

/**
 * SparkScala api 与Java api 不同：
 * 1).java中需要创建 JavaSparkContext
 * 2).scala中是RDD ，java中是JavaRDD
 * 3).scala中将RDD转换成K,V格式的数据直接使用 map转出tuple数据即可
 *    Java中将RDD转换成K,V格式的数据需要使用mapToPair,转出K,V格式的数据
 *
 * 4).JavaPairRDD 在java中代表的是K,V格式的RDD
 *
 */
public class wordcount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("spark_wordcount_java");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("datas");
        JavaRDD<String> words = lines.flatMap(line-> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String, Integer> pairRDD = words.mapToPair(word -> new Tuple2<>(word, 1));
        JavaPairRDD<String, Integer> result = pairRDD.reduceByKey((v1, v2) -> v1 + v2);
        result.foreach(tuple2 -> System.out.println(tuple2));

    }
}
