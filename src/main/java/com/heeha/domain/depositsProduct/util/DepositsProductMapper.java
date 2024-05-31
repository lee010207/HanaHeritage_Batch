//package com.heeha.domain.depositsProduct.util;
//
//import com.heeha.domain.depositsProduct.dto.DepositsProductResponse;
//import com.heeha.domain.depositsProduct.entity.DepositsProduct;
//import com.heeha.domain.depositsProduct.entity.DepositsType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DepositsProductMapper {
//    public static List<DepositsProduct> mapToDepositsProductList(List<DepositsProductResponse> depositsProductResponses) {
//        List<DepositsProduct> depositsProducts = new ArrayList<>();
//
//        for (DepositsProductResponse response : depositsProductResponses) {
//            DepositsProduct depositsProduct = DepositsProduct.builder()
//                    .finPrdtCd(response.getFinPrdtCd())
//                    .type(DepositsType.SAVING)
//                    .dclsMonth(response.getDclsMonth())
//                    .finCoNo(response.getFinCoNo())
//                    .korCoNm(response.getKorCoNm())
//                    .finPrdtNm(response.getFinPrdtNm())
//                    .joinWay(response.getJoinWay())
//                    .mtrtInt(response.getMtrtInt())
//                    .spclCnd(response.getSpclCnd())
//                    .joinDeny(response.getJoinDeny())
//                    .joinMember(response.getJoinMember())
//                    .etcNote(response.getEtcNote())
//                    .maxLimit(response.getMaxLimit())
//                    .dclsStrtDay(response.getDclsStrtDay())
//                    .dclsEndDay(response.getDclsEndDay())
//                    .finCoSubmDay(response.getFinCoSubmDay())
//                    .build();
//
//            depositsProducts.add(depositsProduct);
//        }
//
//        return depositsProducts;
//    }
//}
