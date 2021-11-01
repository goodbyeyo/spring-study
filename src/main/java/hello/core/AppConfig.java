package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Application의 설정정보를 의미
// 스프링 컨테이너는 @Configuration이 붙은 클래스를 설정(구성) 정보로 사용한다
@Configuration
public class AppConfig {    // 팩토리 메서드를 이용해서 Spring Bean을 등록하는 방법

    @Bean   // @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean   // 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
