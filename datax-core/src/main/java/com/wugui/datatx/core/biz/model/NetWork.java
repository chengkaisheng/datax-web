package com.wugui.datatx.core.biz.model;

import java.io.Serializable;
import java.util.*;

/**
 * 图解网络数据结构
 */
public class NetWork implements Serializable {

    //记录两份网络节点,使用空间换时间,提升反向查询效率

    //网络初始化,x_k,y_k
    private Map<String, Map<String, String>> xMapMap;
    //网络初始化,y_k,x_k
    private Map<String, Map<String, String>> yMapMap;
    private String start;

    //回环路径
    private List<String> loopbackList;//回环地址
    //节点深度路径
    private List<List<String>> deepPathList;//深度路径

    private Map<String, String> jobMapStatus;//map任务状态

    public enum TYPE {X,Y}


    public NetWork() {
        xMapMap = new HashMap();//X点集
        yMapMap = new HashMap();//Y点集
        loopbackList = new LinkedList<>();//回环地址
        deepPathList = new LinkedList<>();//深度路径
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 初始化任务
     */
    public void initJob() {
        jobMapStatus = new HashMap<>();
        Set<String> allPoint = getAllPoint();
        allPoint.forEach(job -> jobMapStatus.put(job, "0"));//0,1  0表示未执行,1表示执行
    }


    /**
     * 获取任务的转状态
     *
     * @param job
     * @return
     */
    public String getJobMapStatus(String job) {
        String status = jobMapStatus.get(job);
        return status;
    }

    /**
     * 更新任务的状态
     *
     * @param job    任务名称
     * @param status 任务状态
     */
    public void setJobMapStatus(String job, String status) {
        jobMapStatus.put(job, status);
    }


    public List<String> getLoopbackList() {
        return loopbackList;
    }

    public List<List<String>> getDeepPathList() {
        return deepPathList;
    }

    /**
     * 网络添加节点
     *
     * @param xPoint   x位
     * @param yPoint   y位
     * @param distance 位移
     */
    public void addPoint(String xPoint, String yPoint, String distance) {
        if (!xMapMap.containsKey(xPoint)) {//记录x的索引
            xMapMap.put(xPoint, new HashMap<>());
        }
        xMapMap.get(xPoint).put(yPoint, distance);
        if (!yMapMap.containsKey(yPoint)) {//记录y的索引
            yMapMap.put(yPoint, new HashMap<>());
        }
        yMapMap.get(yPoint).put(xPoint, distance);
    }

    /**
     * 根据坐标获取某点
     *
     * @param xPoint
     * @param yPoint
     * @return
     */
    public String getPoint(String xPoint, String yPoint) {
        Map<String, String> linePoints = xMapMap.get(xPoint);
        String point = linePoints.get(yPoint);
        return point;
    }

    public String getPoint(String point1, String point2, TYPE type) {
        if (type == TYPE.X) {
            Map<String, String> linePoints = xMapMap.get(point1);
            String point = linePoints.get(point2);
            return point;
        } else {
            Map<String, String> linePoints = yMapMap.get(point1);
            String point = linePoints.get(point2);
            return point;
        }
    }

    /**
     * 获取X轴的一列数据
     *
     * @param xPoint
     * @return
     */
    public List<Map<String, String>> getLinePoint(String xPoint) {
        List<Map<String, String>> linePointList = new ArrayList<Map<String, String>>();
        Map<String, String> linePoints = xMapMap.get(xPoint);
        if (linePoints != null) {
            for (Map.Entry<String, String> pointUnit : linePoints.entrySet()) {
                Map<String, String> pointMap = new HashMap<String, String>();
                pointMap.put("X", xPoint);
                pointMap.put("Y", pointUnit.getKey());
                pointMap.put("D", pointUnit.getValue());
                linePointList.add(pointMap);
            }
        }
        return linePointList;
    }

    /**
     * 根据类型获取某轴的一列数据
     *
     * @param point 索引点
     * @param type  类型
     * @return
     */
    public List<Map<String, String>> getLinePoint(String point, TYPE type) {
        List<Map<String, String>> linePointList = new ArrayList<Map<String, String>>();
        if (type == TYPE.X) {
            Map<String, String> linePoints = xMapMap.get(point);
            if (linePoints != null) {
                for (Map.Entry<String, String> pointUnit : linePoints.entrySet()) {
                    Map<String, String> pointMap = new HashMap<String, String>();
                    pointMap.put("X", point);
                    pointMap.put("Y", pointUnit.getKey());
                    pointMap.put("D", pointUnit.getValue());
                    linePointList.add(pointMap);
                }
            }
        } else {
            Map<String, String> linePoints = yMapMap.get(point);
            if (linePoints != null) {
                for (Map.Entry<String, String> pointUnit : linePoints.entrySet()) {
                    Map<String, String> pointMap = new HashMap<String, String>();
                    pointMap.put("X", pointUnit.getKey());
                    pointMap.put("Y", point);
                    pointMap.put("D", pointUnit.getValue());
                    linePointList.add(pointMap);
                }
            }
        }
        return linePointList;
    }


    /**
     * @param netWork    网络节点
     * @param startPoint 起始节点
     * @param pathList   当前任务的路径
     */
    public void recursive(NetWork netWork, String startPoint, ArrayList<String> pathList) {
        if (pathList.contains(startPoint)) {netWork.getLoopbackList().add(pathList.toString() + "->" + startPoint);return;}
        pathList.add(startPoint);
        List<Map<String, String>> linePoint = netWork.getLinePoint(startPoint, NetWork.TYPE.X);
        if (linePoint.size() == 0) {
            ArrayList<String> descList = new ArrayList<>(pathList.size());
            pathList.forEach(path -> descList.add(path));
            netWork.getDeepPathList().add(descList);
        }
        for (Map<String, String> map : linePoint) {recursive(netWork, map.get("Y"), pathList);}
        pathList.remove(startPoint);
    }


    /**
     * 获取所有的点,合并key
     *
     * @return
     */
    public Set<String> getAllPoint() {
        Set<String> allSet1 = xMapMap.keySet();
        Set<String> allSet2 = yMapMap.keySet();
        Set<String> allSet = new HashSet<>();
        allSet.addAll(allSet1);
        allSet.addAll(allSet2);
        return allSet;
    }


    /**
     * 显示路径
     */
    public void show() {
        int placeholder = 3;
        String placeholderSrting = "";
        for (int i = 0; i < placeholder; i++) {placeholderSrting = placeholderSrting + "-";}
        Set<String> allSet = getAllPoint();//获取所有的点,用于遍历
        System.out.print(String.format("%-" + placeholder + "s", ""));
        System.out.print(" ");
        for (String x : allSet) {
            System.out.print(String.format("%-" + placeholder + "s", x));
        }
        System.out.println();
        System.out.print(String.format("%-" + placeholder + "s", "X\\Y"));
        System.out.print(" ");
        for (String ignored : allSet) {System.out.print(placeholderSrting);}
        System.out.println();
        for (String x : allSet) {
            System.out.print(String.format("%-" + placeholder + "s|", x));
            for (String y : allSet) {
                Map<String, String> linePoints = xMapMap.get(x);
                String point = "0";
                if (linePoints != null && linePoints.get(y) != null) {
                    point = linePoints.get(y);
                }
                System.out.print(String.format("%-" + placeholder + "s", point));
            }
            System.out.println();
        }
    }
}
