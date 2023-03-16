package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class singletonTest {

    @Test
    @DisplayName("no spring DI container")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 呼び出すたびに生成
        MemberService memberService1 = appConfig.memberService();
        // 呼び出すたびに生成
        MemberService memberService2 = appConfig.memberService();
        // 1と2比較
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //1と２はちがう
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("SingletonPattern Test")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //same ==
        //equal => java equal

    }

    @Test
    @DisplayName("Yes spring DI container")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 呼び出すたびに生成
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 呼び出すたびに生成
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        // 1と2比較
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //1と２はちがう
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
