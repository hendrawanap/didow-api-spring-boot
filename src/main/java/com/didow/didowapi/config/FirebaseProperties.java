package com.didow.didowapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
  private String serviceAccountUrl;
  private String bucketName;

  public void setServiceAccountUrl(String serviceAccountUrl) {
    this.serviceAccountUrl = serviceAccountUrl;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getServiceAccountUrl() {
    return this.serviceAccountUrl;
  }

  public String getBucketName() {
    return this.bucketName;
  }
}
