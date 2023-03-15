package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("All bean")
    void findAllBean(){
        String[] beanInfos = ac.getBeanDefinitionNames();
        for (String beanInfo : beanInfos) {
            Object bean = ac.getBean(beanInfo);
            System.out.println("bean = " + bean);
        }
    }
}
