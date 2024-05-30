package com.heeha.domain.depositsProduct.service;

import com.heeha.domain.depositsProduct.dto.GetDetailDepositsProductResponse;
import com.heeha.domain.depositsProduct.dto.ProductResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.repository.DepositsProductRepository;
import com.heeha.domain.depositsProduct.util.ProductUtil;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositsProductService {

    private final DepositsProductRepository repository;
    private final ProductUtil productUtil;
    private final DepositsProductRepository depositsProductRepository;

    @Transactional
    public void saveSavingProduct() {
        List<ProductResponse> savingList = productUtil.getSavingList();
        repository.saveAll(savingList
                .stream()
                .map(ProductResponse::toSavingEntity)
                .toList());
    }

    @Transactional
    public void saveDepositProduct() {
        List<ProductResponse> depositList = productUtil.getDepositList();
        repository.saveAll(depositList
                .stream()
                .map(ProductResponse::toDepositEntity)
                .toList());
    }

    public GetDetailDepositsProductResponse getDetail(Long id) {
        Optional<DepositsProduct> depositsProductResponse = depositsProductRepository.findById(id);
        if(depositsProductResponse.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_DEPOSIT_PRODUCT_ID);
        }

        DepositsProduct depositsProduct = depositsProductResponse.get();
        GetDetailDepositsProductResponse response = GetDetailDepositsProductResponse
                .builder()
                .id(depositsProduct.getId())
                .type(depositsProduct.getType().getTitle())
                .finPrdtNm(depositsProduct.getFinPrdtNm())
                .mtrtInt(depositsProduct.getMtrtInt())
                .spclCnd(depositsProduct.getSpclCnd())
                .joinDeny(depositsProduct.getJoinDeny())
                .joinMember(depositsProduct.getJoinMember())
                .etcNote(depositsProduct.getEtcNote())
                .maxLimit(depositsProduct.getMaxLimit())
                .promotionalText(depositsProduct.getPromotionalText())
                .explanatoryText(depositsProduct.getExplanatoryText())
                .build();
        return response;
    }
}
