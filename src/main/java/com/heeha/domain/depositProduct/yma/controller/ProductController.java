package com.heeha.domain.depositProduct.yma.controller;


import com.heeha.domain.depositProduct.yma.service.DepositsProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final DepositsProductService depositProductService;

    @PostMapping("/save")
    public Long saveall() {
        depositProductService.save();
        return 1L;
    }
}
