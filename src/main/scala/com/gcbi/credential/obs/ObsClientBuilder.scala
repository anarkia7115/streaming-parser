package com.gcbi.credential.obs
import com.amazonaws.AmazonWebServiceClient
import awscala.s3._
import awscala.Region
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.services.{ s3 => aws }
import awscala.CredentialsLoader
import awscala.BasicCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.S3ClientOptions
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3Builder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.client.AwsSyncClientParams
import com.amazonaws.ClientConfigurationFactory
import com.amazonaws.regions.Regions

class ObsClientBuilder {
    def build(accessKeyId: String, secretKeyId: String): AmazonS3 = {
      val obsCreds = new BasicAWSCredentials(accessKeyId, secretKeyId)
      val endpoint: String = "obs.cn-north-1.myhuaweicloud.com"
      val region: String = "CN"
      val endpointConf = new EndpointConfiguration(endpoint, region)
      AmazonS3ClientBuilder.standard
        .withCredentials(new AWSStaticCredentialsProvider(obsCreds))
        .withEndpointConfiguration(endpointConf).build()
    }
}