package com.google.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

class SendCallback implements Callback {
      public void onCompletion(RecordMetadata m, Exception e){
          if (e == null){
            System.out.println("Produced a message successfully.");
          } else {
            System.out.println(e.getMessage());
          }
      }
}
