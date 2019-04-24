package com.gcbi.credential.obs

import org.scalatest.FlatSpec
import com.amazonaws.client.AwsSyncClientParams
import scala.collection.JavaConverters._
import com.amazonaws.services.s3.model.ListObjectsRequest

class ObsClientBuilderSpec extends FlatSpec {
    val delim = "/"
    val bucketName = "gcbinlp"
    val accessKeyId = sys.env("AK")
    val secretAcessKey = sys.env("SK")
  
  "Top Level Folder" should "be printed" in {
    
    val obsClientBuilder = new ObsClientBuilder()

    val obs = obsClientBuilder.build(accessKeyId, secretAcessKey)
    
    val listRequest = new ListObjectsRequest()
      .withBucketName(bucketName)
      .withDelimiter(delim)
      
    val folders = obs.listObjects(listRequest).getCommonPrefixes().asScala

    println("bucket name: " + bucketName)
    folders.foreach(objName => {
      println(objName.toString())
    })

  }
  
  "Top N gz of Pubmed Folder" should "be printed" in {
    
    val folderName = "pubmed_baseline/"
    val topN = 10
    
    val obsClientBuilder = new ObsClientBuilder()

    val obs = obsClientBuilder.build(accessKeyId, secretAcessKey)
    
    val listRequest = new ListObjectsRequest()
      .withBucketName(bucketName)
      .withPrefix(folderName)
      .withDelimiter(delim)
      
    val folders = obs.listObjects(listRequest).getObjectSummaries.asScala

    println("bucket name: " + bucketName)
    folders.take(topN).map(_.getKey)
        .filter(_.endsWith("gz"))
        .foreach(objName => {
      println(objName.toString())
    })

  }
}