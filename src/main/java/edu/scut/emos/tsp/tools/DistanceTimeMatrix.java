package edu.scut.emos.tsp.tools;

import edu.scut.emos.tsp.model.DTKey;
import edu.scut.emos.tsp.model.DTValue;
import edu.scut.emos.tsp.model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;

public class DistanceTimeMatrix {

    public static HashMap<DTKey, DTValue> computeDistanceTimeTable(Position[] positionArray) {
        Date start = new Date();

        HashMap<DTKey, DTValue> dtMap = new HashMap<>();
        if (positionArray.length <= 50){
            getRouterMatrix(positionArray,dtMap);
            System.out.println(dtMap);
        }else {
            //todo
        }
//        for (int i = 0; i < positionArray.length; i++) {
//            for (int j = i; j < positionArray.length; j++) {
//                DTKey key = new DTKey(positionArray[i], positionArray[j]);
//                DTValue value = null;
//
//                //利用API计算两个点之间的距离和时间
//                value = getTravelDistance(key);
//
//                if (!dtMap.containsKey(key)) {
//                    dtMap.put(key, value);
//                }
//
//                // 两个Position交换，并存入Map中
//                DTKey revertKey = key.revert();
//                if (!dtMap.containsKey(revertKey)) {
//                    dtMap.put(revertKey, value);
//                }
//            }
//        }
        Date end = new Date();
        System.out.println("api: " + (end.getTime() - start.getTime()));
        return dtMap;
    }
    public static void  getRouterMatrix(Position[] positionArray,HashMap<DTKey,DTValue> dtMap){
        int length = positionArray.length;
        Position[] test;
        if (length <=50){
            for (int i = 0; i < length-1; i++) {
                Position[] tmpPosition = new Position[length -i-1];
                int index = 0;
                for (int j = i+1; j < length; j++) {
                    tmpPosition[index++] = positionArray[j];
                }
                test = tmpPosition;
                getRouteVector(positionArray[i],test,dtMap);
            }
            //if the position is equal,it duration and time is equals to zero;
            for (int j = 0; j< length; j++){
                dtMap.put(new DTKey(positionArray[j],positionArray[j]),new DTValue(0,0));
            }
        }else {
            //todo
        }
    }

    private static void getRouteVector(Position originPosition, Position[] distanceSet,HashMap<DTKey,DTValue> dtmap) {
      
       StringBuilder destinationString = new StringBuilder("");

        for (int i = 0; i < distanceSet.length ; i++) {
            destinationString.append(distanceSet[i].toString()+"|");
        }
        destinationString.delete(destinationString.length()-1,destinationString.length());

        String url = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins="+originPosition.toString()+"&destinations="+destinationString+"&ak="+Parameters.AK;
        String json = loadJSON(url);
        JSONObject wholeJson = new JSONObject(json);
        JSONArray resultArry = wholeJson.getJSONArray("result");

        int index = 0;
        for (Object jsonObject : resultArry){
            double duration = ((JSONObject) jsonObject).getJSONObject("duration").getDouble("value")/3600.0;
            double distance = ((JSONObject) jsonObject).getJSONObject("distance").getDouble("value")/1000.0;
            dtmap.put(new DTKey(originPosition,distanceSet[index]),new DTValue(distance,distance));
            index++;
        }
    }

//    public static DTValue getTravelDistance(DTKey positions) {
//        Position one = positions.getOne();
//        Position two = positions.getTwo();
//
//        if (one.equals(two)) {
//            return new DTValue(0, 0);
//        }
//        String url = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=" +
//                one.toString() + "&destinations=" + two.toString() + "&ak=" + Parameters.AK;
//        String json = loadJSON(url);
//
//        JSONObject jb;
//        jb = new JSONObject(json);
//
//        if (jb.getInt("status") != 0) {
//            return null;
//        }
//
//        JSONArray resultArray = jb.getJSONArray("result");
//        JSONObject resultObject = resultArray.getJSONObject(0);
//        JSONObject distanceObject = resultObject.getJSONObject("distance");
//        JSONObject durationObject = resultObject.getJSONObject("duration");
//        double durationValue = durationObject.getDouble("value");
//        double distanceValue = distanceObject.getDouble("value");
//        return new DTValue(distanceValue / 1000.0, durationValue / 3600.0);
//    }

    private static String loadJSON(String url) {
        //处理字符串
        StringBuilder json = new StringBuilder();
        try {
            //通过一个表示URL地址的字符串可以构造一个URL对象。
            //url构造函数需要的参数。
            URL oracle = new URL(url);
            //yc是URLConnection对象，oracle.openConnection()返回的是URLConnection对象，赋值给yc。
            URLConnection yc = oracle.openConnection();

            //BufferedReader缓冲方式文本读取
            //InputStreamReader是字节流与字符流之间的桥梁，能将字节流输出为字符流，
            //并且能为字节流指定字符集，可输出一个个的字符
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "utf-8"));// 防止乱码
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static void main(String[] args) {
       //getRouterMatrix(new Position[]{DataGenerate.generateStochasticPosition(),DataGenerate.generateStochasticPosition(),DataGenerate.generateStochasticPosition()},new HashMap<>());
       computeDistanceTimeTable(new Position[]{DataGenerate.generateStochasticPosition(),DataGenerate.generateStochasticPosition(),DataGenerate.generateStochasticPosition()});
        System.out.println(5);
    }
}
