package edu.scut.emos.tsp.tools;

import edu.scut.emos.tsp.model.Position;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AddressTranslation {
    public static String addressTranslation(Position position) {
        if (position == null)
            return null;

        String Ak = Parameters.AK;
        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + position.toString() + "&output=json&pois=0&ak=" + Ak;

        String json = loadJSON(url);
        JSONObject jb;
        jb = new JSONObject(json);

        JSONObject resulObject = jb.getJSONObject("result");
        String addressObject = resulObject.getString("formatted_address");

        return addressObject;
    }

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
        } catch (IOException e) {
        }
        return json.toString();
    }

}
