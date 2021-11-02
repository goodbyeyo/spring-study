package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // final 이 있으면 값이 반드시 할당되어야 한다(보통 생성자를 통해 값을 반드시 넣도록 구현)
    // 단 생성자가 1개만 있으면 @Autowired 를 생략해도 된다

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
        @Autowired //(required = false) 선택적 의존관계에 사용할수있다
        public void setMemberRepository(MemberRepository memberRepository) {
            System.out.println("memberRepository = " + memberRepository);
            this.memberRepository = memberRepository;

        }

        @Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
            System.out.println("discountPolicy = " + discountPolicy);
            this.discountPolicy = discountPolicy;
        }
    */

/*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1.OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
