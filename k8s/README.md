## Deploy and Access the Kubernetes Dashboard
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

### Deploy
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml
```
### Access
```
kubectl proxy
```
Kubectl will make Dashboard available at http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/.

### Create Admin User
```
kubectl apply -f dashboard-admin.yaml
```
```
kubectl -n kubernetes-dashboard create token admin-user
```

## Strimzi Operator
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

## Kafka
Create Kafka Cluster
```
kubectl apply -f kafka.yaml
```

Find the port of the bootstrap service:
```
kubectl get service my-cluster-kafka-external-bootstrap -n my-kafka-project -o=jsonpath='{.spec.ports[0].nodePort}{"\n"}'
```
Find the IP address of the node:
```
kubectl get nodes --output=jsonpath='{range .items[*]}{.status.addresses[?(@.type=="InternalIP")].address}{"\n"}{end}'
```