# BitcoinTransactionTracking

Prerequisites: Apache Kafka, Redis, Flask, Redis-cli, MySQL, eclipse

The index.jsp file has a button which onclick binds to the websocket API and starts tracking all live transactions. This is sent to the servlet which connects to kafka producer and also pushes the frequency data into redis. The consumer receives these messsages and pushes it to DB to persist it.

Steps to be followed to run the project:-
1. Download the src and Webcontent folder and place them appropriately in the project
2. Add the following jars to the classpath: jedis-3.1.0.jar, json-simple-1.1.jar,kafka_2.12-0.11.0.1.jar, kafka_clients-0.11.0.1,jar, mysql-connector.jar, slf4j-api-1.7.25.jar
3. Start zookeeper and kafka server
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
4. Create topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic transaction
5. Set retention time
bin/kafka-topics.sh --zookeeper--alter --topic as transaction --config retention.ms=86400000
6. Create table kafkadata with 4 columns timestamp, hash, amount, size all of type varchar. Here db name is kafka, username is root no password 


