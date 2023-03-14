package hello.core.Member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberSerivceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }

//    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given
        Member member = new Member(1L,"MemberTest", Grade.VIP);
        //when
        memberService.join(member);
        Member findOne = memberService.findMember(1l);
        //then
        Assertions.assertThat(member).isEqualTo(findOne);

    }
}
