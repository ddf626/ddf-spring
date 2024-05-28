package com.ddf.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext {

    /**
     * 从xml文件中读取的beanDefinition都放在这里
     */
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    /**
     * 实例化出来的单例对象都放在这里
     * key: bean.id  value:bean.instance
     */
    private Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        // 读取xml文件
        this.readXml(fileName);
        // 实例化bean对象
        this.instanceBeans();
    }

    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            // 处理配置文件中的每一个<bean>标签
            for (Element element : rootElement.elements()) {
                String beanId = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                beanDefinitions.add(new BeanDefinition(beanId, beanClassName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射创建bean实例，并存储在beanMap中
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                beanMap.put(beanDefinition.getId(),
                            Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

}
