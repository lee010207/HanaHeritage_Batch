package com.heeha.domain.depositsProduct.service;

import com.heeha.domain.depositsProduct.dto.ProductResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.repository.DepositsProductRepository;
import com.heeha.domain.depositsProduct.util.ProductUtil;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositsProductService {

    private final DepositsProductRepository repository;
    private final ProductUtil productUtil;

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

    public DepositsProduct getProduct(Long savingProductId) {
        return repository.findById(savingProductId).orElseThrow(
                /** TO-DO: 예외 바꿔야함 **/
                () -> new BaseException(BaseResponseStatus.DUPLICATE_CUSTOMER));
    }
}
