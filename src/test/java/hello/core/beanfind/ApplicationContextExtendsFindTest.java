package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("サブクラスが二つ存在する場合、エラー（Duplicated）")
    void findByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("サブクラスが二つ存在する場合、Bean名前設定時、エラー回避")
    void findByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("特定サブタイプで探索")
    void findBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("親タイプを基準に探索")
    void findAllBeansByParentType(){
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("親タイプを基準に探索 - Object")
    void findAllBeansByObjectType(){
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value = " + beans.get(key));
        }
    }

}
