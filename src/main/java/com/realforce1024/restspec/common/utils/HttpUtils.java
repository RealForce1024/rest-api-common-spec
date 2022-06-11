package com.realforce1024.restspec.common.utils;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 编程燃风 RealForce1024
 */
public class HttpUtils {
    public static String assemble(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url + "?");
        params.forEach((key, value) -> sb.append(String.format("%s=%s&", key, value)));
        return sb.toString();
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "kobe");
        map.put("age", "24");
        System.out.println(assemble("/users", map));
    }

    @SneakyThrows
    public static String getPostData(ServletRequest request) {
        StringBuilder sb = new StringBuilder();

        ServletInputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }

        return sb.toString();
    }
}
