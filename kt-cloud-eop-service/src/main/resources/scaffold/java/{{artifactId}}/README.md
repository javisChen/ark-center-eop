# 介绍
{{artifactId}}

# 运行

## Docker Build
```shell
docker build -f ./{{artifactId}}-start/Dockerfile -t {{artifactId}}:v1 ./{{artifactId}}-start
```
## Docker Run
```shell
docker run -p 8083:8083 --name {{artifactId}} -d \
-e NACOS_DISCOVERY_IP=172.24.80.20 \
-e NACOS_DISCOVERY_SERVER_ADDR=172.24.80.20:8848 \
-e NACOS_CONFIG_SERVER_ADDR=172.24.80.20:8848 \
-e SYS_OPT=-DSpring.profiles.active=dev \
{{artifactId}}:v1
```

# 软件架构

## 组件说明

| 组件名称                       | 描述           | 版本    |
|----------------------------|--------------|-------|
| {{artifactId}}-acl     | 防腐层          | 1.0.0 |
| {{artifactId}}-api     | 开放接口层        | 1.0.0 |
| {{artifactId}}-dao     | 数据访问层        | 1.0.0 |
| {{artifactId}}-manager | 通用业务处理层      | 1.0.0 |
| {{artifactId}}-service | 具体业务以及内部访问转发 | 1.0.0 |
| {{artifactId}}-start   | 启动层          | 1.0.0 |


## 使用说明
```
mvn install到本地（如果不想运行测试，可以加上-DskipTests参数）
```