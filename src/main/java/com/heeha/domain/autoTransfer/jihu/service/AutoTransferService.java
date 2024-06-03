package com.heeha.domain.autoTransfer.jihu.service;


import com.heeha.domain.autoTransfer.jihu.dto.AutoTransferReq;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AutoTransferService {
    private static final Logger log = LoggerFactory.getLogger(AutoTransferService.class);

    @Transactional
    public void autoTransfer(AutoTransferReq autoTransferReq) {

    }

    

}
