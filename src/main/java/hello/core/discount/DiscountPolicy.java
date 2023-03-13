package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @param member
     * @param Price
     * @return 割引金額
     */
    int discount(Member member, int Price);
}
