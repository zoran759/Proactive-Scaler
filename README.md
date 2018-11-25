# SpringBoot_influxDB
SpringBoot + influxDB + grafana

There are two spring-boot apps here:
* app-with-metrics
    sends CPU and RAM metrics to local influx db
* proactive-scaler
    reads metrics from influx db 

### How to run
### method 1:

* start influx db locally
```bash
docker run -p 8086:8086 \
           -v influxdb:/var/lib/influxdb \
              influxdb
```

* start the app with IDEA or java -jar

### method 2 (Docker-compose):

* start app and database in docker-compose
```bash
docker-compose up

```

### method 3 (Kubernetes - Minikube):
```bash
k create -f kubernetes/app-with-metrics.yaml
k expose deployment metrics-app --type=NodePort
minikube service metrics-app --url
```

### Queries for grafana:
```
SELECT count("id") FROM "connection" WHERE $timeFilter GROUP BY time(1s) fill(null)
SELECT mean("cpu") FROM "workload" WHERE $timeFilter GROUP BY time(1s) fill(null)
SELECT count("method") FROM "connection" WHERE ("method" = 'cpu') AND $timeFilter GROUP BY time(10s) fill(null)
```

<h3>Data in InfluxDB</h3>
<img src="https://pp.vk.me/c638331/v638331767/bfde/QnsfkyVDEGg.jpg" alt="influx" />

<h3>Dashboard in Grafana</h3>
<img src="https://pp.vk.me/c638331/v638331767/bfd6/CCIxqKysD8U.jpg" alt="grafana" />

