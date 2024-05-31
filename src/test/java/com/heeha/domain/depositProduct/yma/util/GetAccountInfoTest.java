package com.heeha.domain.depositProduct.yma.util;

import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.domain.signDeposit.service.SignDepositService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetAccountInfoTest {

@Autowired
private SignDepositService signDepositService;

long accountID = 2;



    @Test
    void getSavingList() {
        signDepositService.getAccountInfo(accountID);
    }
}