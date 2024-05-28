package com.ddf.spring.test;

import com.ddf.spring.BeansException;
import com.ddf.spring.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) context.getBean("aService");
        aService.hello();
    }

}
