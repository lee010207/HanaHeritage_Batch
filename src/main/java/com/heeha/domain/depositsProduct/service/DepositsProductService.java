package com.heeha.domain.depositsProduct.service;

import com.heeha.domain.depositsProduct.dto.DepositsProductResponse;
import com.heeha.domain.depositsProduct.dto.GetListDepositsProductResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.repository.DepositsProductRepository;
import com.heeha.domain.depositsProduct.util.DepositsProductUtil;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public DepositsProduct getProduct(Long savingProductId) {
        return repository.findById(savingProductId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.EMPTY_DEPOSITS_PRODUCT));
    }

    @Transactional
    public List<GetListDepositsProductResponse> getList() {
        List<DepositsProduct> depositsProductList = repository.findAll();
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
        List<DepositsProduct> depositsProductList = repository.findByserchwordLike(searchword);
        if(depositsProductList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.EMPTY_DEPOSITS_PRODUCT);
        }
        return depositsProductList
                .stream()
                .map(GetListDepositsProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
}