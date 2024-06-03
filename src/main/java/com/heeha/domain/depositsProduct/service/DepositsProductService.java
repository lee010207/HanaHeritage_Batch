package com.heeha.domain.depositsProduct.service;

import com.heeha.domain.depositsProduct.dto.DepositsProductResponse;
import com.heeha.domain.depositsProduct.dto.GetDetailDepositsProductResponse;
import com.heeha.domain.depositsProduct.dto.GetListDepositsProductResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.repository.DepositsProductRepository;
import com.heeha.domain.depositsProduct.util.DepositsProductUtil;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositsProductService {

    private final DepositsProductRepository depositsProductRepository;
    private final DepositsProductUtil depositsProductUtil;

    @Transactional
    public void saveSavingProduct() {
        List<DepositsProductResponse> savingList = depositsProductUtil.getSavingList();
        depositsProductRepository.saveAll(savingList
                .stream()
                .map(DepositsProductResponse::toSavingEntity)
                .toList());
    }

    @Transactional
    public void saveDepositProduct() {
        List<DepositsProductResponse> depositList = depositsProductUtil.getDepositList();
        depositsProductRepository.saveAll(depositList
                .stream()
                .map(DepositsProductResponse::toDepositEntity)
                .toList());
    }

    public DepositsProduct getDetail(Long id) {
        Optional<DepositsProduct> depositsProductResponse = depositsProductRepository.findById(id);
        if(depositsProductResponse.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_DEPOSIT_PRODUCT_ID);
        }

        return depositsProductResponse.get();
    }

    @Transactional
    public List<GetListDepositsProductResponse> getList() {
        List<DepositsProduct> depositsProductList = depositsProductRepository.findAll();
        if(depositsProductList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.EMPTY_DEPOSITS_PRODUCT);
        }
        return depositsProductList
                .stream()
                .map(GetListDepositsProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetListDepositsProductResponse> searchList(String searchword) {
        List<DepositsProduct> depositsProductList = depositsProductRepository.findByserchwordLike(searchword);
        if(depositsProductList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.EMPTY_DEPOSITS_PRODUCT);
        }
        return depositsProductList
                .stream()
                .map(GetListDepositsProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
}