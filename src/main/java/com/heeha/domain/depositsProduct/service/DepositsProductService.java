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