package com.heeha.domain.depositProduct.yma.util;

import com.heeha.domain.depositsProduct.service.DepositsProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductUtilTest {

    @Autowired
    private DepositsProductService depositProductService;


    @Test
    void getSavingList() {
        depositProductService.saveSavingProduct();
        depositProductService.saveDepositProduct();
    }
}