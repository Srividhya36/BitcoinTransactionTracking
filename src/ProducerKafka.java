import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
public class ProducerKafka {
		
		public void producer(String amount, String hash, String size) throws InterruptedException {
	        Properties props = new Properties();
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
	        String formattedDate = sdf.format(date);
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
	        ProducerRecord<String, String> data;
	        data = new ProducerRecord<String, String>("transaction", 0, formattedDate, amount+";"+hash+";"+size);
	        producer.send(data);
	            Thread.sleep(1L);
	        producer.close();
	    }

	}


