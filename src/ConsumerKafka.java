
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import java.sql.*;
import java.text.SimpleDateFormat;  
public class ConsumerKafka {
    public void Myconsumer() throws InterruptedException{
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("odd"));
        ConsumerRecords<String, String> recs = consumer.poll(1);
        String[] params = String.valueOf(recs).split(";");
        try{  
        	Class.forName("com.mysql.jdbc.Driver");  
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka","root","");
        	Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
            String formattedDate = sdf.format(date);
        	PreparedStatement stmt=con.prepareStatement("insert into kafkadata values(?,?,?,?)");  
        	stmt.setString(1,params[0]);//1 specifies the first parameter in the query  
        	stmt.setString(2,params[1]);  
        	stmt.setString(3,params[2]);  
        	stmt.setString(4,formattedDate);  
        	stmt.executeUpdate();  
        	con.close();  
        	}
        catch(Exception e){ System.out.println(e);}  
        	}


    }
            

    
