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
@Configuration    //Configuration을 사용하지 않으면 싱글톤이 보장되지 않는다
public class AppConfig {    // 팩토리 메서드를 이용해서 Spring Bean을 등록하는 방법

    // @Bean memberService -> new MemmoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // call 예상순서
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제로는 각각 1번씩만 호출된다
    // -> 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른클래스를 만들고
    // -> 그 다른 클래스를 스프링 빈으로 등록한것

    @Bean   // @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean   // 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
