package com.zt.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用main方法测试HttpClient技术
 */
public class TesthttpClient {
    public static void main(String[] args) {
        try {
            testGetParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 有参数GET请求
     */
    public static void testGetParams() throws IOException, URISyntaxException {
        // 创建客户端对象
        HttpClient client = HttpClients.createDefault();
        // 基于Builder构建请求地址
        URIBuilder builder = new URIBuilder("http://localhost:80/params");
        // 基于单参数传递，构建请求地址
//        builder.addParameter("name", "zt");
//        builder.addParameter("password", "admin");
//        URI uri = builder.build();
        // 基于多参数传递，构建请求地址
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("name", "zt"));
        nvps.add(new BasicNameValuePair("password", "admin"));
        builder.addParameters(nvps);
        URI uri = builder.build();

        // 发起请求，接受响应对象
        HttpResponse response = client.execute(new HttpGet(uri));
        // 获取响应体，响应数据是json
        HttpEntity entity = response.getEntity();
        // 避免乱码
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("服务器响应数据是: " + responseString);
        // 回收资源
        client = null;
    }

    /**
     * 无参数GET请求
     * 使用HttpClient。访问web服务的过程
     * 1、创建客户端，相当于打开浏览器
     * 2、创建请求地址，相当于输入地址
     * 3、发起请求，相当于访问网站
     * 4、处理相应结果，相当于浏览器显示结果
     */
    public static void testGetNoParams() throws IOException {
        // 创建客户端对象
        HttpClient client = HttpClients.createDefault();
        // 创建请求地址
        HttpGet get = new HttpGet("http://localhost:80/test");
        // 发起请求，接受响应对象
        HttpResponse response = client.execute(get);
        // 获取响应体，响应数据是json
        HttpEntity entity = response.getEntity();
        // 避免乱码
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("服务器响应数据是: " + responseString);
        // 回收资源
        client = null;
    }
}
