package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//@ComponentScan => @Component 붙은 클래스를 찾아서 Spring Bean으로 등록해준다
@Configuration
@ComponentScan( // AppConfig를 스캔대상에서 제외하기위해서 excludeFilters 설정
        basePackages = "hello.core.member", // 탐색할 패키지의 시작위치를 지정, 설정정보클래스의 위치를 프로젝트 최상단에 두는것이 좋음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
