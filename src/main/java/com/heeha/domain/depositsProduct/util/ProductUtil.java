package com.heeha.domain.depositsProduct.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heeha.domain.depositsProduct.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.simple.parser.JSONParser;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductUtil {

    @Value(value = "${app.key}")
    private final String API_KEY;
    private final String topFinGrpNo = "020000";
    private final String pageNo = "1";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<ProductResponse> getSavingList() {
        String savingUrl = "https://finlife.fss.or.kr:443/finlifeapi/savingProductsSearch.json?"
                + "auth=" + API_KEY
                + "&topFinGrpNo=" + topFinGrpNo
                + "&pageNo=" + pageNo;
        return callApi(savingUrl);
    }

    public List<ProductResponse> getDepositList() {
        String depositUrl = "https://finlife.fss.or.kr:443/finlifeapi/depositProductsSearch.json?"
                + "auth=" + API_KEY
                + "&topFinGrpNo=" + topFinGrpNo
                + "&pageNo=" + pageNo;
        return callApi(depositUrl);
    }

    private List<ProductResponse> callApi(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> apiResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return parseResponses(apiResponse);
    }

    private List<ProductResponse> parseResponses(ResponseEntity<String> response) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonData = (JSONObject) parser.parse(response.getBody());
            JSONObject result = (JSONObject) jsonData.get("result");
            JSONArray products = (JSONArray) result.get("baseList");
            return Arrays.asList(mapper.readValue(products.toJSONString(), ProductResponse[].class));
        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

