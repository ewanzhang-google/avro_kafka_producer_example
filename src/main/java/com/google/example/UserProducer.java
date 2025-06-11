
package com.google.example;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

  public class UserProducer {

    private static Properties configure() throws Exception {
        Properties p = new Properties();
        p.load(new java.io.FileReader("client.properties"));
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        return p;
    }

    public static void main(String[] args) throws Exception {
        Properties p = configure();

        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(p);
        final User u = new User("SchemaEnthusiast", 42);
        final String topicName = "newUsers";
        ProducerRecord<String, User>  message =
          new ProducerRecord<String, User>(topicName, "", u);
        producer.send(message, new SendCallback());
        producer.close();
    }
  }
