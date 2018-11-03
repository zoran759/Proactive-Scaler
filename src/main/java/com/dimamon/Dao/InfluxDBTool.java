package com.dimamon.Dao;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


/**
 * Created by dimamon on 11/16/16.
 */
@Repository
@Qualifier("Influx")
public class InfluxDBTool {

    private InfluxDB influxDB;
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static int UDP_PORT = 8086;
    private final static String DB_NAME = "studentsTest";

    //SetUp
    {
        this.influxDB = InfluxDBFactory
                .connect("http://localhost:" + UDP_PORT, USERNAME, PASSWORD);
    }

    //Измерить показатели, method - вызываемый метод
    public void measure(int id, String method) {

        BatchPoints batchPoints = BatchPoints
                .database(DB_NAME)
//                .tag("async", "true")
                .retentionPolicy("autogen")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        Point point1 = Point.measurement("connection")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("id", id)
                .addField("method", method)
                .build();

        batchPoints.point(point1);

        this.write(batchPoints);
    }

    //Запись измерений в базу
    private void write(BatchPoints batchPoints) {
        influxDB.write(batchPoints);
    }


//        Достать из базы
//        Query query = new Query("SELECT idle FROM cpu", DB_NAME);
//        influxDB.query(query);

}
