package com.gcbi.stream.writer

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import com.gcbi.stream.StreamingLogger
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import awscala._, s3._
import scala.collection.mutable.Buffer

object PubmedToHDFS {
  private val sparkConf = new SparkConf().setAppName("Pubmed To HDFS") 
  private val sc = new SparkContext(sparkConf)
  
  def listObs(path: String): Array[String] = {
    val fs = FileSystem.get(sc.hadoopConfiguration)
    val status = fs.listStatus(new Path(path))
    status.map(s => s.getPath.toString())
  }
  
  def main(args: Array[String]) {
    /*
     * Read <host> and <port>
     * */
    if (args.length < 2) {
      System.err.println("Usage: NetworkWordCount <hostname> <port>")
      System.exit(1)
    }
    
    StreamingLogger.setStreamingLogLevels()
    
    val ssc = new StreamingContext(this.sparkConf, Seconds(1))
    
    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)

  }
}