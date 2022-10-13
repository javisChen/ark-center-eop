# 介绍

# 运行
EOP依赖改造过的mybatis-generator

## Docker Build
```shell
docker build -f ./ark-center-eop-start/Dockerfile -t ark-center-eop:v1 ./ark-center-eop-start
```
## Docker Run
```shell
docker run --name ark-center-eop -d -p 8082:8080 \
-e NACOS_DISCOVERY_IP=172.24.80.20 \
-e NACOS_DISCOVERY_SERVER_ADDR=172.24.80.20:8848 \
-e NACOS_CONFIG_SERVER_ADDR=172.24.80.20:8848 \
-e SYS_OPT=-DSpring.profiles.active=dev \
ark-center-gateway:v1
```