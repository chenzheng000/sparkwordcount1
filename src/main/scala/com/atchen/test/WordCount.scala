package com.atchen.test


import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    //1.创建SparkConf并设置App名称，设置本地模式运行
    val conf = new SparkConf().setAppName("WC").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)

    //3.使用sc创建RDD，输入和输出路径都是本地路径
    sc.textFile("datas").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile("output2")

    //4.关闭连接
    sc.stop()
  }
}



