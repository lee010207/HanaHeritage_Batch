package com.heeha.transfer;

import com.heeha.domain.account.jihu.dto.TransferRequest;
import com.heeha.domain.account.jihu.service.TransferService;
import com.heeha.domain.customer.dto.SignUpRequest;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class accountTransferTest {
    @Autowired
    TransferService transferService;

    @Test
    public void selectTest() throws Exception{
//        TransferRequest TransferRequest = new TransferRequest("");
//        transferService.findByCustomerId(TransferRequest);

    }
}
