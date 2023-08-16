
# Simple Web Service

порт сервера: 8000

package ru.ddk.simplewebservice.controller.api

## API Reference

контроллер StatusController
#### Check health

```http
  GET /health
```

| Response | Type     | Description                |
| :-------- | :------- | :------------------------- |
|  | `string` | {"status": "OK"} |


## docker hub
### логин
docker login
### сборка
docker build --platform linux/amd64 -t rangdemon/docker-arch-ddk:simple-ws-amd64 .
### старт
docker run --name sws_ddk -p 8001:8001 -d rangdemon/docker-arch-ddk:simple-ws-amd64_2
### публикация
docker push rangdemon/docker-arch-ddk:simple-ws-amd64
### спулить
docker pull rangdemon/docker-arch-ddk:simple-ws-amd64

## k8s
манифесты лежат в каталоге /k8s
настроен редирект URL с /otusapp/{name}/ на /health

## развернуть миникуб на HV
pvs - это комутатор, настроить до подьема миникуба в диспетчере Hyper-V

minikube delete && cd {каталог с конфигом для inginx контроллера} 
&& minikube start --image-repository=auto --vm-driver hyperv --hyperv-virtual-switch "PVS" --kubernetes-version=v1.24.3 
&& kubectl create namespace {имя спейса} 
&& helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ 
&& helm repo update && helm install nginx ingress-nginx/ingress-nginx --namespace {имя спейса} -f {название файла с конфигон для контроллера} 
&& minikube ip

## применить конфиг
kubectl -n {имя спейса} apply -f {имя конфига} 

## docker neo4j
docker run -d --name testneo4j -p7474:7474 -p7687:7687 --env NEO4J_AUTH=neo4j/password neo4j:5.10

## новый образ
docker pull rangdemon/docker-arch-ddk:simple-ws-amd64_2

## swagger
http://arch.homework/swagger-ui/index.html

## helm rep for neo4j
helm -n ddk repo add neo4j https://helm.neo4j.com/neo4j

## up helm repo
helm -n ddk repo update

## ставим prometheus operator (без него не будет servicemonitor)
### в nginx_ingress добавить servicemonitor
minikube addons disable ingress
helm -n ddk repo add stable https://charts.helm.sh/stable
helm -n ddk repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm -n ddk repo update
helm -n ddk install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml --atomic
helm -n ddk install nginx ingress-nginx/ingress-nginx -f nginx_ingress.yml --atomic

## прокинуть порт
kubectl -n ddk port-forward service/prom-grafana 9000:80
kubectl -n ddk port-forward service/prom-kube-prometheus-stack-prometheus 9090
### логин/пароль от графаны
admin/prom-operator
## настройка prometheus
важно проверить в таргетах источник метрик
 