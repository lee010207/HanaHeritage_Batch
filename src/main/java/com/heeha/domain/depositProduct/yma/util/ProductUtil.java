package com.heeha.domain.depositProduct.yma.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heeha.domain.depositProduct.yma.dto.DepositsProductResponse;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductUtil {

    @Value(value = "${app.key}")
    private String key;
    private String topFinGrpNo = "020000";
    private String pageNo = "1";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<DepositsProductResponse> getSavingList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        // deposit 정기예금 saving 적금

        String depositurl = "https://finlife.fss.or.kr:443/finlifeapi/depositProductsSearch.json?"
                + "auth=" + key
                + "&topFinGrpNo=" + topFinGrpNo
                + "&pageNo=" + pageNo;


        String savingurl = "https://finlife.fss.or.kr:443/finlifeapi/savingProductsSearch.json?"
                + "auth=" + key
                + "&topFinGrpNo=" + topFinGrpNo
                + "&pageNo=" + pageNo;


        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<String> depositresponse = restTemplate.exchange(
                depositurl,
                HttpMethod.GET,
                entity,
                String.class
        );

        ResponseEntity<String> savingresponse = restTemplate.exchange(
                savingurl,
                HttpMethod.GET,
                entity,
                String.class
        );


        JSONParser parser = new JSONParser();
        try {
            JSONObject depositjsonObject = (JSONObject) parser.parse(depositresponse.getBody());
            JSONObject savingjsonObject = (JSONObject) parser.parse(savingresponse.getBody());
            JSONObject depositresult = (JSONObject) depositjsonObject.get("result");
            JSONObject savingresult = (JSONObject) savingjsonObject.get("result");

            JSONArray depositproductList = (JSONArray) depositresult.get("baseList");
            JSONArray savingproductList = (JSONArray) savingresult.get("baseList");

            List<DepositsProductResponse> depositproducts = Arrays.asList(mapper.readValue(depositproductList.toJSONString(), DepositsProductResponse[].class));
            List<DepositsProductResponse> savingproducts = Arrays.asList(mapper.readValue(savingproductList.toJSONString(), DepositsProductResponse[].class));

            List<DepositsProductResponse> depositsProducts = new ArrayList<>();
            depositsProducts.addAll(depositproducts);
            depositsProducts.addAll(savingproducts);

            return depositsProducts;

        } catch (ParseException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

