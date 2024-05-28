package com.ddf.spring.beans;

import com.ddf.spring.core.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {

    private BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");

            beanFactory.registerBeanDefinition(new BeanDefinition(beanId, beanClassName));
        }
    }

}
