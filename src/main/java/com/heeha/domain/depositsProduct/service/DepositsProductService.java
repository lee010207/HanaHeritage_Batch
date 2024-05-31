package com.heeha.domain.depositsProduct.service;

import com.heeha.domain.depositsProduct.dto.DepositsProductResponse;
import com.heeha.domain.depositsProduct.repository.DepositsProductRepository;
import com.heeha.domain.depositsProduct.util.DepositsProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositsProductService {

    private final DepositsProductRepository repository;
    private final DepositsProductUtil depositsProductUtil;

    @Transactional
    public void saveSavingProduct() {
        List<DepositsProductResponse> savingList = depositsProductUtil.getSavingList();
        repository.saveAll(savingList
                .stream()
                .map(DepositsProductResponse::toSavingEntity)
                .toList());
    }

    @Transactional
    public void saveDepositProduct() {
        List<DepositsProductResponse> depositList = depositsProductUtil.getDepositList();
        repository.saveAll(depositList
                .stream()
                .map(DepositsProductResponse::toDepositEntity)
                .toList());
    }
}
