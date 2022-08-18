# Strimzi Operator
https://operatorhub.io/operator/strimzi-kafka-operator

Install Operator Lifecycle Manager (OLM), a tool to help manage the Operators running on your cluster.
```
curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.21.2/install.sh | bash -s v0.21.2
```
Install the operator by running the following command:
```
kubectl create -f https://operatorhub.io/install/strimzi-kafka-operator.yaml
```
After install, watch your operator come up using next command.
```
kubectl get csv -n operators
```

# Kafka Cluster
Create namespace:
```
kubectl create namespace kafka-streams
```
Create/Update Kafka Cluster:
```
kubectl apply -f kafka.yaml -n kafka-streams
```
Create topics:
```
kubectl apply -f kafka-streams-kafkatopic.yaml -n kafka-streams
```

# Kafka Streams Spring Boot Project
Package the project:
```
mvn clean package -Dmaven.test.skip
```
Build the image:
```
docker build -t kafka-streams-spring-boot .
```
Deploy the project:
```
kubectl apply -f kafka-streams-spring-boot.yaml -n kafka-streams
```
