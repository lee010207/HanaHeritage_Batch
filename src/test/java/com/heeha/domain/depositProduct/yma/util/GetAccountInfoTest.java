package com.heeha.domain.depositProduct.yma.util;

import com.heeha.domain.account.service.AccountInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetAccountInfoTest {

@Autowired
private AccountInfoService accountInfoService;

long accountID = 2;



    @Test
    void getSavingList() {
        accountInfoService.getAccountInfo(accountID);
    }
}