# Spring Cloud Stream - Kafka Streams
Sample project which illustrates how to use Spring Cloud Stream whit Kafka Streams.

### Kafka Directory
[Download](https://kafka.apache.org/downloads) the latest Kafka release and extract it:
```
$ tar -xzf kafka_2.13-3.2.0.tgz
$ mv kafka_2.13-3.2.0.tgz kafka
$ cd desenvolvimento/kafka
```

### Start Kafka
```
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
```
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Create Topics
```
$ bin/kafka-topics.sh --create --topic tp_prospects --bootstrap-server localhost:9092
$ bin/kafka-topics.sh --create --topic tp_accounts --bootstrap-server localhost:9092
$ bin/kafka-topics.sh --create --topic tp_enrichment_accounts --bootstrap-server localhost:9092
$ bin/kafka-topics.sh --create --topic tp_clients --bootstrap-server localhost:9092
```

### Message Producer
```
$ bin/kafka-console-producer.sh --topic tp_prospects --bootstrap-server localhost:9092 --property parse.key=true --property key.separator=":"
> 1:{"id": 1, "name": "augusto"}
```
```
$ bin/kafka-console-producer.sh --topic tp_accounts --bootstrap-server localhost:9092 --property parse.key=true --property key.separator=":"
> 1:{"id": 1, "accountNumber": "000.000.000-0"}
```
```
$ bin/kafka-console-producer.sh --topic tp_enrichment_accounts --bootstrap-server localhost:9092 --property parse.key=true --property key.separator=":"
> 1:{"id": 1, "status": "REGULAR"}
```

### Message Consumer
```
$ bin/kafka-console-consumer.sh --topic tp_clients --from-beginning --bootstrap-server localhost:9092  --property parse.key=true --property key.separator=":"
```

## Table-Table Join
Code:
![Diagram](docs/imgs/table_table_join_code.png)

Result:
![Diagram](docs/imgs/table_table_join_kafka_messages.png)