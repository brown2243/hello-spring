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

- DDL : Data Define Language 의 약자로, 스키마/도메인/테이블/뷰/인덱스를 정의/변경/제거할 때 사용하는 언어이다.

  - 테이블을 생성하고, 테이블 내용을 변경하고, 테이블을 없애버리는 것.
  - 흔히 CREATE, ALTER, DROP 을 떠올리면 된다.

- DML : Data Manipulation Language 의 약자로, Query(질의)를 통해서 저장된 데이터를 실질적으로 관리하는 데 사용한다.

  - 테이블 안의 데이터 하나하나를 추가하고 삭제하고 수정하는 것.
  - 흔히 INSERT, DELETE, UPDATE 를 떠올리면 된다.

- DCL : Data Control Language 의 약자로, 보안/무결성/회복/병행 제어 등을 정의하는데 사용한다. 데이터 관리 목적.

  - 흔히 COMMIT, ROLLBACK, GRANT, REVOKE 를 떠올리면 된다.
  - COMMIT : Transaction의 변경 내용을 최종 반영한다고 재판 결정하는 것.
  - ROLLBACK : Transaction의 변경 내용을 모두 취소하고 이전 상태로 되돌리는 것.
    (Transaction : Database에서 하나의 Logical Function을 수행하는 단위. 즉, 작업 하나의 단위. 하나의 Transaction은 COMMIT되거나 ROLLBACK되어야 한다. 하나의 Transaction은 정상적으로 종료되면 COMMIT을 비정상적으로 종료되면 ROLLBACK 수행. )
    [출처] DDL, DML, DCL 차이 정리|작성자 Junee01

## 스프링 통합 테스트

- @SpringBootTest

  - 스프링 컨테이너와 테스트를 함께 실행한다.
  - 통합 테스트(Integration Test)를 지원하는 어노테이션
  - Spring Boot 전체 컨텍스트를 초기화(빈 등록, 의존성 주입 등).
  - @Autowired나 @Inject를 통해 테스트에서 사용할 빈(서비스, 리포지토리 등)을 주입받을 수 있게 함

- @Transactional
  - 테스트에서 사용 시, 테스트 메서드 실행 후 자동 롤백
  - 처음부터 둘다 붙여놓고 데이터가 왜 저장이 안되는지 헤맸다...

## 스프링 jdbc template

- 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다.
  - jdbc driver api로 작성하던 코드를 줄이고 줄인 jdbcTemplate!
  - template 디자인 패턴을 적용했다 함
- 하지만 SQL은 직접 작성해야 한다.
- dataSource를 injection 받아야 함

- 생성자가 하나고, spring bean으로 등록되면 autowird 생략가능
  - 암묵적 생성자 주입(implicit constructor injection)

```java
// 여러 row 일수도 있으니 List인건 알겠다
// 파리미터로 전달받은 id를 명시적으로 넘기지 않는데 ?에 왜 id가 들어가는걸까?

// 오타심
List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper());
    return result.stream().findAny();

```

## JPA(java persistance api)

- JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
- JPA를 사용하면 개발 생산성을 크게 높일 수 있다.

- GenerationType.IDENTITY = db 자동 생성

- id는 find으로 조회가능
- pk가 아닌 칼럼은 JPQL 사용해야 함
- 쿼리 사용하려면 JPQL이라는 객체지향 쿼리를 써야함
- `em.createQuery("select m from Member m", Member.class).getResultList();`
  - 이미 매핑이 되어있음
- @Transactional 필수

  - 영속성 컨텍스트(Persistence Context) = 1차 캐시
  - EntityManager가 관리하는 DB와 메모리 사이의 중간 계층.
  - 트랜잭션이 시작되면 EntityManager가 영속성 컨텍스트를 생성하고,
  - 트랜잭션이 커밋될 때 flush() → commit() 순서로 DB에 반영돼.
  - 👉 @Transactional이 없으면 영속성 컨텍스트 자체가 제대로 생기지 않거나, flush/commit이 호출되지 않음.
  - react-query와 유사한 면이 있다.

- 복잡한 쿼리를 어떻게 처리하나?
- 기존의 복잡한 시스템도 다 JPA로 처리가능

## Spring Data JPA

- Spring boot + Spring data JPA = 개발 생산성 향상
- 구현체 없이 interface만으로 동작하는건 진짜 신기하긴 하더라.
- 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이라
- JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 한다 하심

- spring data JPA가 인터페이스를 바탕으로 구현체를 생성해 bean에 등록
- 공통화로 추출할 수 있는 메서드들은 다 이미 구현 되어있음
- 규칙을 가지고 이름을 작성하면 그것까지 구현해줌
- queryDSL을 사용하면 쿼리도 코드로 작성가능
- JPA + Spring Data JPA + queryDSL 조합
  - 우리 서비스도 동일 스택
  - JPA도 네이티브 쿼리가능
  - typeORM은 위 기능 전부다 지원했던 것 같은데...

# 8. AOP

## AOP가 필요한 상황

- AOP: Aspect Oriented Programming
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)

- 처음에는 클래스 메타데이터 로딩등으로 요청이 오래 걸림
- 성능이 필요한 서버들은 웜업?도 한다하심

- 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
- 시간을 측정하는 로직은 공통 관심 사항이다.
- 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
- 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.

## AOP 적용

- AOP는 명확하게 알 수 있게 컴포넌트 스캔보다 `@Configuration`에 bean으로 등록하는 편
- `@Around("execution(* hello.hello_spring..*(..) )")`

- 특정 문법으로 대상을 지정가능

- 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
- 핵심 관심 사항을 깔끔하게 유지할 수 있다.
- 변경이 필요하면 이 로직만 변경하면 된다.
- 원하는 적용 대상을 선택할 수 있다.

- Spring AOP는 대상 객체의 프록시 객체를 생성
- 메서드 호출 전에 AOP 로직(Advice)을 실행
- 그 후, 실제 대상 객체의 메서드를 호출

# 9. 다음으로

야생 로드맵

1. ORM JPA 기본편
2. 실전 spirng boot + JPA 1
3. 실전 spirng boot + JPA 2
4. spring data jpa
5. 실전 queryDsl

# outro

학습기간이 25/04/01 ~ 25/04/06으로 굉장히 달려서 들었는데 한번쯤 배워보고 싶었던 주제 + 실무에서 사용해야 함

두가지의 상황이 시너지로 작용해서 즐기면서 들었다. 확실히 당장 사용할, 하는 기술을 공부하는게 재밌다.

불편 했던 점

1. 도메인식 폴더구조(com.example.\*) - 폴더구조가 깊어짐
2. layer 별 구조로 controller, service, entity, repository가 다른 디렉토리에 흩어져 있어 흐름이 끊김
   1. 전형적인 계층형 구조(Controller → Service → Repository → Entity)는 DDD(Domain-Driven Design)나 클린 아키텍처의 영향이라 한다
   2. 익숙하지 않아서 그런듯하다. nestJS는 기능별로 모듈화
3. 단일 파일에 하나의 클래스 - 파일이 너무 많아진다.
   1. 자바는 오직 클래스로만 객체 생성이 가능하기에, 클래스가 많을 수 밖에 없다.

---

좋았던 점

1. spring이 세팅이 복잡하다는데, boot로 프로젝트 세팅하면 쉽다.
2. Optional 로 Nullable을 처리하는게 깔끔하다 느꼈다.
3. spring data JPA로 인터페이스만으로 동작하는 것과, queryDsl의 자동 Qclass 생성은 진짜 신기하더라.

하다보면서 뭔가 더 많았던 것 같은데 정리하려니 이것밖에 생각이 안난다.

더 배워가면서, 느껴 볼 예정이다.
