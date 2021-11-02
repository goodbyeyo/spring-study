package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 키워드가 필드를 모아서 생성자를 자동으로 만들어준다
// 룸북이 자바의 애노테이션 프로세서라는 기능을 이용해서 컴파일 시점에 코드(생성자)를 자동으로 생성해준다
public class OrderServiceImpl implements OrderService {

    // final 이 있으면 값이 반드시 할당되어야 한다(보통 생성자를 통해 값을 반드시 넣도록 구현하거나 초기화 값을 넣는다)
    // 생성자 주입을 사용하면 필드에 final 키워드를 사용할수 있다.
    // 생성자에서 혹시라도 값이 설ㄹ정되지 않는 오류를 컴파일 시점에 막아준다
    // 단 생성자가 1개만 있으면 @Autowired 를 생략해도 된다

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1.OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

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
/*
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
*/

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
