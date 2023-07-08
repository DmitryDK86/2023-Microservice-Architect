
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
# логин
docker login
# сборка
docker build --platform linux/amd64 -t rangdemon/docker-arch-ddk:simple-ws-amd64 .
# старт
docker run --name sws_ddk -p 8000:8000 -d rangdemon/docker-arch-ddk:simple-ws-amd64
# публикация
docker push rangdemon/docker-arch-ddk:simple-ws-amd64
# спулить
docker pull rangdemon/docker-arch-ddk:simple-ws-amd64
