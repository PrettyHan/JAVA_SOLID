package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 割引テスト")
    void vip_o() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        // when
        int discount = discountPolicy.discount(memberA, 10000);
        // then
        assertThat(discount).isEqualTo(1000);

    }


    @Test
    @DisplayName("NO VIP 10% 割引テスト")
    void vip_x() {
        // given
        Member memberB = new Member(2L, "memberB", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(memberB, 10000);
        // then
        assertThat(discount).isEqualTo(0);
    }

}