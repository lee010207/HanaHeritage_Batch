package com.heeha.domain.depositsProduct.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heeha.domain.depositsProduct.dto.DepositsProductResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.entity.DepositsType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<DepositsProduct> getSavingList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<DepositsProduct> depositsProductList = new ArrayList<>();

        // deposit 예금 saving 적금

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

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(depositresponse.getBody());
            JSONObject result = (JSONObject) jsonResponse.get("result");
            JSONArray baseList = (JSONArray) result.get("baseList");

            List<DepositsProductResponse> depositProductsResponse = objectMapper.readValue(
                    baseList.toJSONString(),
                    new TypeReference<List<DepositsProductResponse>>() {}
            );

            List<DepositsProduct> depositsProductList1 = DepositsProductMapper.mapToDepositsProductList(depositProductsResponse);
            depositsProductList.addAll(depositsProductList1);




        } catch (ParseException | IOException e) {
            throw new RuntimeException("Failed to parse response", e);
        }
        return depositsProductList;
    }

//        ResponseEntity<String> savingresponse = restTemplate.exchange(
//                savingurl,
//                HttpMethod.GET,
//                entity,
//                String.class
//        );
//
//        List<DepositsProductResponse> savingProductsResponse = depositresponse.getBody();
//
//        System.out.println("+++++++++++==");
//        for(DepositsProductResponse a : savingProductsResponse) {
//            System.out.println(a.toString());
//        }
//        List<DepositsProduct> depositsProducts = DepositsProductMapper.mapToDepositsProductList(savingProductsResponse);
////
//        JSONParser parser = new JSONParser();
//        try {
//            JSONObject depositjsonObject = (JSONObject) parser.parse(depositresponse.getBody());
//            JSONObject savingjsonObject = (JSONObject) parser.parse(savingresponse.getBody());
//            JSONObject depositresult = (JSONObject) depositjsonObject.get("result");
//            JSONObject savingresult = (JSONObject) savingjsonObject.get("result");
//
//            JSONArray depositproductList = (JSONArray) depositresult.get("baseList");
//            JSONArray savingproductList = (JSONArray) savingresult.get("baseList");
//
//
//            for (Object obj : depositproductList) {
//                System.out.println("++++++++");
//                System.out.println(obj);
//                //obj를 DepositsProduct로 변환
//                JSONObject jsonObject = (JSONObject) obj;
//                jsonObject.put("type", DepositsType.DEPOSIT);
//            }
//
//            for (Object obj : savingproductList) {
//                JSONObject jsonObject = (JSONObject) obj;
//                jsonObject.put("type", DepositsType.SAVING);
//            }

//
//            List<DepositsProductResponse> depositproducts = Arrays.asList(mapper.readValue(depositproductList.toJSONString(), DepositsProductResponse[].class));
//            List<DepositsProductResponse> savingproducts = Arrays.asList(mapper.readValue(savingproductList.toJSONString(), DepositsProductResponse[].class));

//
//            for (DepositsProductResponse product : savingproducts) {
//                product.setType(DepositsType.SAVING);

//
//
//        for(DepositsProduct e : depositsProducts) {
//            System.out.println("+++++++++");
//            System.out.println(e.toString());
//        }

//            depositsProducts.addAll(depositsProducts);
//            depositsProducts.addAll(savingproducts);


//        } catch (ParseException e) {
//            throw new RuntimeException();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
        }



