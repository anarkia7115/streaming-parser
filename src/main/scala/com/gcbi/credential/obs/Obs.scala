package com.gcbi.credential.obs

import com.amazonaws.services.s3.model.ListObjectsRequest
import scala.collection.JavaConverters._
import com.amazonaws.services.s3.AmazonS3Client
import scala.collection.mutable.Buffer
import com.amazonaws.services.s3.AmazonS3
import org.pmw.tinylog.Logger

class Obs(accessKeyId: String, 
    secretKeyId: String) {
  val client = new ObsClientBuilder().build(accessKeyId, secretKeyId)
  val delim = "/"
  
  def this() =
    this(sys.env("AK"), sys.env("SK"))
  
  def listFolders(bucketName:String): Buffer[String] = {
    
    val listRequest = new ListObjectsRequest()
      .withBucketName(bucketName)
      .withDelimiter(delim)
      
    val folders = this.client.listObjects(listRequest).getCommonPrefixes().asScala

    folders
  }
  
  def listFiles(bucketName: String, prefix: String, suffix: String="", limit:Int=0): Buffer[String] = {
    val listRequest = new ListObjectsRequest()
      .withBucketName(bucketName)
      .withPrefix(prefix)
      .withDelimiter(delim)
      
    println("bucketName: " + bucketName)
    println("prefix: " + prefix)

    val files = this.client
      .listObjects(listRequest)
      .getObjectSummaries.asScala
      .map(_.getKey)

    Logger.info("size of files: " + files.size)
    println("size of files: " + files.size)
    val filtered_files = if(suffix.size > 0) files.filter(_.endsWith(suffix)) else files
    
    if(limit==0) filtered_files else filtered_files.take(limit)

  }
  
}


