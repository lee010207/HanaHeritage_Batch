package com.heeha.domain.depositProduct.yma.service;

import com.heeha.domain.depositProduct.entity.DepositProduct;
import com.heeha.domain.depositProduct.yma.dto.DepositsProductResponse;
import com.heeha.domain.depositProduct.yma.repository.DepositsProductRepository;
import com.heeha.domain.depositProduct.yma.util.ProductUtil;
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
    public void save() {
        List<DepositsProductResponse> depositsList = productUtil.getSavingList();
        try {
            List<DepositProduct> depositsProducts = repository.saveAll(depositsList
                    .stream()
                    .map(DepositsProductResponse::toEntity)
                    .toList());
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_CUSTOMER);
        }
    }
}
