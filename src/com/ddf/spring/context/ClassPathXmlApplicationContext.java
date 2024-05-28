package com.ddf.spring.context;

import com.ddf.spring.beans.BeanDefinition;
import com.ddf.spring.beans.BeanFactory;
import com.ddf.spring.beans.BeansException;
import com.ddf.spring.beans.SimpleBeanFactory;
import com.ddf.spring.beans.XmlBeanDefinitionReader;
import com.ddf.spring.core.ClassPathXmlResource;
import com.ddf.spring.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory {

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinition(resource);
        this.beanFactory = beanFactory;
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }
}
