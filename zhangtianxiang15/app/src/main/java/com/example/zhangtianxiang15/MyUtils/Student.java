package com.example.zhangtianxiang15.MyUtils;

import java.text.NumberFormat;

/**
 * Student类
 */
public class Student {
    private String name;
    private String id;
    private Double android;
    private Double spark;
    private Double datamining;
    private Double echarts;
    private Double score;

    public Student(String name, String id,
                   Double android, Double spark,
                   Double datamining, Double echarts) {
        this.name = name;
        this.id = id;
        this.android = android;
        this.spark = spark;
        this.datamining = datamining;
        this.echarts = echarts;
        this.score = makescore(android, spark, datamining, echarts);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAndroid() {
        return android;
    }

    public void setAndroid(Double android) {
        this.android = android;
    }

    public Double getSpark() {
        return spark;
    }

    public void setSpark(Double spark) {
        this.spark = spark;
    }

    public Double getDatamining() {
        return datamining;
    }

    public void setDatamining(Double datamining) {
        this.datamining = datamining;
    }

    public Double getEcharts() {
        return echarts;
    }

    public void setEcharts(Double echarts) {
        this.echarts = echarts;
    }

    public Double getScore() {
        return score;
    }

    public Double makescore(Double android, Double spark,
                            Double datamining, Double echarts) {
        Double dd = (3.0 * (Math.max((android - 50.0), 0.0) / 10.0) +
                4.0 * (Math.max(0.0, (spark - 50.0)) / 10.0) +
                4.0 * (Math.max(0.0, (datamining - 50.0)) / 10.0) +
                4.0 * (Math.max(0.0, (echarts - 50.0)) / 10.0)) / 15.0;
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        String str = format.format(dd);
        return Double.parseDouble(str);
    }

}
