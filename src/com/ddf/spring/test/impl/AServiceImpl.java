package com.ddf.spring.test.impl;

import com.ddf.spring.test.AService;

public class AServiceImpl implements AService {
    @Override
    public void hello() {
        System.out.println("AServiceImpl hello....");
    }
}
