package com.oh3g.nextrend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oh3g.nextrend.config.NaverApiConfig;
import com.oh3g.nextrend.dto.datalab.request.DatalabRequestDto;
import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverShoppingInsightService {
    private final NaverApiConfig naverApiConfig;

    public List<DatalabResponseDto> callNaverShoppingTrendsAPI(DatalabRequestDto datalabRequestDto) {

        String apiUrl = "https://openapi.naver.com/v1/datalab/shopping/category/keyword/age";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", naverApiConfig.getClientId());
        requestHeaders.put("X-Naver-Client-Secret", naverApiConfig.getClientSecret());
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = convertObjectToJsonString(datalabRequestDto);
        log.info(requestHeaders.toString());
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                ObjectMapper objectMapper = new ObjectMapper();
                List<DatalabResponseDto> responseDtoList =
                        objectMapper.readValue(con.getInputStream(), new TypeReference<List<DatalabResponseDto>>() {});
                return responseDtoList;
            } else {  // 에러 응답
                String errorResponse = readBody(con.getErrorStream());
                throw new RuntimeException("API 요청이 실패했습니다. 응답 코드: " + responseCode + ", 에러 메시지: " + errorResponse);
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

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

    private String convertObjectToJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 변환에 실패했습니다.", e);
        }
    }
}