package com.gcbi.stream.writer

import org.scalatest.FlatSpec

class PubmedToHDFSSpec extends FlatSpec {
  val pubmedToHdfs = PubmedToHDFS

  "file name list" should "be print to obs" in {
    val pubmedFolder = "pubmed_baseline"
    pubmedToHdfs.listObs(pubmedFolder).map(println)
  }
  
}