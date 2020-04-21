package com.tutorial.springboot.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WebDocumentSearchApi {

    public String[] Search(String Search_target) {
        String clientId = "9t2P1BZHgY8BnfH5_O33"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "LL2mfKoK9Y"; //애플리케이션 클라이언트 시크릿값"

        String[] responseBody = new String[100];
        String[] Community = {"dcinside","오늘의유머","엠엘비파크","여성시대"};

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        for(int i =0;i<Community.length;i++) {
            String text = EncodingUTF8(Search_target, Community[i]);
            String apiURL = "https://openapi.naver.com/v1/search/webkr?query=" + text + "&sort=date";    // json 결과
            responseBody[i] = get(apiURL, requestHeaders);
        }
        return responseBody;
    }

    private String EncodingUTF8(String Seach_Target,String Community) {
        String text = Seach_Target + " " + Community;
        try {
            text = URLEncoder.encode(Seach_Target, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        return text;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
