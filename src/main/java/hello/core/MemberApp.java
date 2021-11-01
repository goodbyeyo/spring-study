package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

//        MemberService memberService = new MemberServiceImpl();

        // ApplicationContext는 스프링 컨테이너에 해당함
        // @Bean으로 등록한 모든 객체들을 관리한다
        // @Configuration 붙은 Class 를 Parameter로 등록
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // @Bean에서 등록한 메서드 이름을 꺼내와야 한다, 두번째는 반환타입 지정
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
