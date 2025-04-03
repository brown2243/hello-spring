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
