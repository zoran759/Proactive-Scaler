package com.dimamon;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

class LoadTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadTest.class);

    public static String URL = "http://192.168.99.100:30904";

    public static final List<Double> DATASET_1 = Arrays.asList(
            30., 32., 35., 40., 38., 51., 50., 49., 55., 50., 40., 59., 35., 36., 40.
    );
    public static final List<Double> DATASET_2 = Arrays.asList(
            40., 43., 40., 47., 45., 52., 50., 60., 55., 64., 62., 69., 74., 75., 80.
    );

    @Test
    void loadTestCpu() {
        DATASET_2.stream().forEach(val -> {

            final String url = URL + "/workload/cpu/" + val;
            LOGGER.info("connect: {}", url);
            URLConnection connection = null;
            try {
                connection = new URL(url).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream inputStream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }




    @Test
    void loadTest() throws IOException, InterruptedException {
        for (int i = 0; i < 10000 ; i++) {
            Thread.sleep(1000);
            final String url = "http://localhost:8081/students/2";
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
        }
    }

    @Test
    void loadCPU() throws IOException, InterruptedException {
        for (int i = 0; i < 50000 ; i++) {
//            Thread.sleep(2);
            final String url = "http://localhost:8081/workload/cpu";
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
        }
    }

    @Test
    void loadRAM() throws IOException, InterruptedException {
        for (int i = 0; i < 10000 ; i++) {
//            Thread.sleep(100);
            final String url = "http://localhost:8081/workload/ram";
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
        }
    }
}
