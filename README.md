# 1. 강의 소개

- 동작하는 웹 어플리케이션을 만드는게 가장 빠르게 배우는 방법이다.
- 만들면서 큰 그림을 그려보자.
- 스프링 학습의 첫 길잡이 역활

### 스프링 정복 로드맵

- 스프링 입문(해당 강의)
- 스프링 핵심 원리
- 스프링 웹 MVC
- 스프링 DB 데이터 접근 기술
- 실전 스프링 부트!

# 2. 프로젝트 환경 설정

## 프로젝트 생성

- 자바 & 에디터 설치
- 스프링 부트 스타터 - https://start.spring.io/
- Gradle이 메인
- snapshot, m1등은 정식 release 버전이 아님
- artifact는 빌드의 결과물

### dependency

- spring web
- thymeleaf

- spring이 세팅할게 많아 손이 많이 갔는데 다양한 기능을 boot가 한번에 제공해 개발 편의도모
- `mavenCentral()` : mavenCentral 해당 사이트에서 패키지를 다운 받도록

- spring boot는 tomcat을 내장해 자체적으로 띄움

## 라이브러리 살펴보기

- spring-boot-starter: boot + core + logging
  - logging은 slf4j는 interface, 구현체는 logback
    - 스프링이 표준으로 사용중
- spring-boot-starter-web
  - spring-boot-starter-tomcat
  - spring-mvc
- spring-boot-starter-test
  - junit: 자바진영 메인 테스트 프레임워크
  - mockito: mock 라이브러리
  - assertj: test code 쉽게 작성 도와줌
  - spring-test: spring 통합 테스트 지원

`./gradlew dependencies` : 세부 dependency 확인

## View 환경설정

- spring이 java web app의 개발 생태계를 지원하는 엄청 큰 프레임워크라 다 알기는 거의 불가능 하다.
- 필요한 내용을 찾는 능력이 중요

- thymeleaf 를 사용한 템플릿을 작성하는데 손으로 타이핑 했더니 코드가 있네…
- 인텔리제이는 `<p th:text="'안녕하세요' + ${data}">안녕하세요 손님</p>` 의 data까지 찾아줌
  - 초기값이고, model의 속성을 받아 html에서 사용할 수 있게 하는 구조
  - model은 return 하지 않고
  - 템플릿 파일명을 return → view resolver가 해당 UI를 찾음
- controller → model → view resolver
  - `resources:templates/` + viewName + `.html`

## 빌드하고 실행하기

```java
./gradlew bootRun
./gradlew build -x test
./gradlew clean build
./gradlew clean build
java -jar build/libs/hello-spring-0.0.1-SNAPSHOT.jar
```

## 3. 스프링 웹 개발 기초

### 정적 컨텐츠

- 변하지 않는 컨텐츠 제공
- tomcat → controller
  - 컨트롤러가 없다면 static 파일 찾음
  - 반환

### MVC와 템플릿 엔진

- 동적으로 변경한 html을 제공
- MVC: model - view - controller
- 예전에는 view에서 모든걸 다했다함 JSP → Model 1 방식
  - JSP 파일 하나가 수천줄도 가능(DB, 비즈니스로직…)
- 관심사 분리를 위해 MVC로 분리 됌
- tomcat → controller
  - 컨트롤러가 있다면 실행
  - return으로 view resolver가 template 찾음

### API

- JSON으로 client에게 데이터 전달
- tomcat → controller
  - 컨트롤러가 있다면 실행
  - `@ResponseBody` 이 없다면, return을 spring은 template view resolver에게 전달
  - `@ResponseBody` 이 있다면, return을 `HttpMessageConverter`에게 전달
    - 문자는 그냥 넘겨주면 됌 - StringConverter
    - 객체라면? 기본적으로 JSON으로 반환 - JsonConverter
  - HTTP accept header, controller return type으로 `HttpMessageConverter` 선택

# 4. 회원 관리 예제 - 백엔드 개발

## 비즈니스 요구사항 정리

- 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
- 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황으로 가정
- 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용

### 일반적 웹 앱 계층 구조

- 컨트롤러: 웹 MVC의 컨트롤러 역할
- 서비스: 핵심 비즈니스 로직 구현
- 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

## 회원 도메인과 리포지토리 만들기

코드 작성

## 회원 리포지토리 테스트 케이스 작성

- unit 테스트의 장점 설명
- 테스트는 랜덤적으로 실행되어도 결과가 변하지 않게 작성
- AfterEach 테스트 메서드가 끝나면 콜백처럼 실행

## 회원 서비스 개발

코드작성

## 회원 서비스 테스트

- 테스트 케이스는 한글로도 많이 적는다.
- given -> when -> then

# 5. 스프링 빈과 의존관계

## 컴포넌트 스캔과 자동 의존관계 설정

- `@Controller` 있으면, 스프링이 객체 생성 및 관리
- 스프링 컨테이너에서 스프링 빈이 관리된다 라는 표현을 사용
- 하나의 서비스를 여러 컨트롤러에서 사용할 수 있는데, 전통적으로 new 로 생성하면 다른 객체를 사용함
  - JS에선 파일에서 객체를 생성하고 참조를 export 하는게 가능
- 스프링 컨테이너에 등록하면 하나만 생성되고, 여러 부가적 장점이 있음!
- `@AutoWired`이 붙으면 스프링 컨테이너가 객체 실행시, 등록된 객체의 참조를 주입
- DI

### 스프링 빈을 등록하는 2가지 방법

- `컴포넌트 스캔`과 `@AutoWired` 자동 의존관계 설정
- 자바 코드로 직접 등록

- `@Component` 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
- 시작점은 `@SpringBootApplication`

  - `@Controller`
  - `@Service`
  - `@Repository`

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {

	/**
	 * Alias for {@link Component#value}.
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

- 스프링 빈은 기본적으로 싱글톤으로 동작
  - 메모리 절약
  - 설정으로 싱글톤이 아니게 가능하지만 일반적으로 사용안함

## 자바 코드로 직접 스프링 빈 등록하기

- 아래처럼 넣어줌

```java
@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
```

- `XML`로도 설정 가능(레거시)

### DI 3가지 방법

- **생성자 주입** - 가장 권장 하는 방식
- 필드 주입 `@Autowried`
  - 바꿔 줄 수 있는 방법이 없음
- setter 주입 `@Autowried`
  - 한번 세팅하면 바꿀 일이 없는데 퍼블릭으로 노출 됌

ai 참조

### 1. ✅ 생성자 주입 (가장 권장됨)

```java
@Component
public class MyService {
    private final MemberRepository memberRepository;

    // 의존성을 생성자에서 주입
    public MyService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
```

- **장점**
  - 💡 **불변성 보장**: `final` 키워드를 통해 의존성 변경 방지 가능.
  - 💡 **테스트 용이성**: 생성자를 통해 명시적으로 의존성을 주입 → 테스트 코드에서 쉽게 mock 주입 가능.
  - 💡 **컴파일 타임 체크**: 의존성이 누락되면 컴파일 또는 애플리케이션 시작 시점에 오류 발생.
  - 💡 **명확한 의존성 표시**: 코드만 봐도 어떤 의존성이 필요한지 한눈에 알 수 있음.

---

### 2. ❌ 필드 주입 (`@Autowired` 직접 필드에)

```java
@Component
public class MyService {
    @Autowired
    private MemberRepository memberRepository;
}
```

- **단점**
  - ⚠️ **불변성 보장 X**: `final`을 사용할 수 없고, 런타임 시 변경 가능.
  - ⚠️ **테스트 어려움**: 리플렉션을 써야 의존성을 바꿀 수 있어서 mocking 어렵고 번거로움.
  - ⚠️ **컴포넌트 스캔에 의존**: 의존성 주입이 런타임에 일어나서, 명시적인 생성자 호출과 달리 IDE 지원이나 리팩토링 시 약함.
  - ⚠️ **은닉된 의존성**: 필드만 보고는 클래스 외부에서 어떤 의존성이 필요한지 알기 어려움.

---

### 3. ⚠️ Setter 주입 (필요한 경우만 사용)

```java
@Component
public class MyService {
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
```

- **장점**
  - 의존성 변경이 가능함 (선택적 의존성이나 순환 참조 해결 시 유용)
- **단점**

  - `public` setter를 노출해야 하므로 캡슐화가 깨짐
  - 변경되지 않아야 할 의존성도 외부에서 바꿀 수 있음

- **항상 생성자 주입을 기본으로 사용하세요.**
- **Lombok의 `@RequiredArgsConstructor`**를 쓰면 생성자 주입을 깔끔하게 만들 수 있어요.
  ```java
  @RequiredArgsConstructor
  @Service
  public class MyService {
      private final MemberRepository memberRepository;
  }
  ```

# 6. 회원 관리 예제 - WEB MVC개발

## 홈 화면 추가

- 정적 페이지보다 controller가 선순위!

## 등록

## 조회

# 7. 스프링 DB 접근 기술

## H2 데이터베이스 설치

- MYSQL + Dbeaver로 대체
- JPA 도 오래된 기술로 스프링에서 쓰게 한번 래핑한게 Spring data JPA

## 순수 JDBC
```
# build.gradle
implementation 'org.springframework.boot:spring-boot-starter-jdbc' 
runtimeOnly 'com.mysql:mysql-connector-j'

# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hello?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=
spring.datasource.password=
```
- 자바는 DB연결하려면 JDBC 드라이버가 필수
- 급 여의도 SI 썰... 순수한 코드...
- JdbcMemberRepository만 들고 컨피그에서 갈아 끼워 준 것만으로 디비바꿈
- OOP의 다형성
  - 스프링에서 이런게 편하게 지원해줌 DI
- 과거에는 기존의 코드의 수정이 필요했었다.
- 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
- 영한쌤 피셜 이게 객체지향의 매력

## 스프링 통합 테스트

## 스프링 jdbc template

## JPA

## 스프링 데이터 JPA
