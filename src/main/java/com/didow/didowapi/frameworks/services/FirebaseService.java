package com.didow.didowapi.frameworks.services;

import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.stereotype.Component;
import com.didow.didowapi.config.FirebaseProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

@Component("firebaseService")
public class FirebaseService {
  private FirebaseProperties firebaseProperties;

  public FirebaseService(FirebaseProperties firebaseProperties) throws IOException {
    this.firebaseProperties = firebaseProperties;

    if (FirebaseApp.getApps().isEmpty()) {
      var serviceAccount = new FileInputStream(this.firebaseProperties.getServiceAccountUrl());
      var credentials = GoogleCredentials.fromStream(serviceAccount);
      var options = FirebaseOptions.builder().setCredentials(credentials).build();
      FirebaseApp.initializeApp(options);
    }
  }

  public Firestore getDB() {
    return FirestoreClient.getFirestore();
  }

  public Bucket getBucket() {
    return StorageClient.getInstance().bucket(this.firebaseProperties.getBucketName());
  }
}
