# 介绍

# 运行

## Docker Build
```shell
docker build -f ./kt-cloud-eop-start/Dockerfile -t kt-cloud-eop:v1 ./kt-cloud-eop-start
```
## Docker Run
```shell
docker run --name kt-cloud-eop -d -p 8082:8080 \
-e NACOS_DISCOVERY_IP=172.24.80.20 \
-e NACOS_DISCOVERY_SERVER_ADDR=172.24.80.20:8848 \
-e NACOS_CONFIG_SERVER_ADDR=172.24.80.20:8848 \
-e SYS_OPT=-DSpring.profiles.active=dev \
kt-cloud-gateway:v1
```