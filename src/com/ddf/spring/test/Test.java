package com.ddf.spring.test;

import com.ddf.spring.beans.BeansException;
import com.ddf.spring.context.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) context.getBean("aService");
        aService.hello();
    }

}
