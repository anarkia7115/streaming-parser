package com.gcbi.credential.obs

import org.scalatest.FlatSpec

class ObsSpec extends FlatSpec {
  "Top level" should "be listed" in {
    val obs = new Obs()
    val bucketName = "gcbinlp"
    
    obs.listFolders(bucketName).foreach(println)
  }
  
  "Some gz" should "be listed" in {
    val obs = new Obs()
    val bucketName = "gcbinlp"
    val folder_name = "pubmed_baseline"
    val suffix = "gz"
    obs.listFiles(bucketName, folder_name).foreach(println)
  }
}