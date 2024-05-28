package com.ddf.spring;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();

    private Map<String, Object> beanMap = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = beanMap.get(beanName);
        if (bean != null) {
            return bean;
        }

        int i = beanName.indexOf(beanName);
        if (i == -1) {
            throw new BeansException("bean not found");
        }

        BeanDefinition beanDefinition = beanDefinitions.get(i);
        try {
            bean = Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("instance bean fail");
        }

        beanMap.put(beanName, bean);

        return bean;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
