# make_pdf_test
PDF 생성하여, 다운로드 해주는 테스트 프로젝트.

코드 개선은 없을 예정.

사내에서 PDF 다운로드 기능을 제공해 주어야 하는부분으로 인해 Test겸 작성된, Demo 프로젝트

- 환경
spring boot 2.0
+ embaded tomcat + JPA + mariaDB
+ pdfBox 2.0.8

- 실행법
mariaDB 설치
설치 후, application.properties 에서 datasource 프로퍼티 수정
주석처리된, jpa 프로퍼티를 이용하여 table 및 data를 초기화 해주자.

spring boot DemoApplication 을 실행.


* 결론적으로 다건 PDF 생성 후, zip 형태로 다운로드를 서비스 하려고 가공하려면 너무 느리다. 그리고 메모리가 부족한 경우 OOM이 발생할 확률이 매우 높다. 이를 해결하기 위해서는 JVM Heap 메모리를 조정 또는 PDF의 내용을 제한을 걸어야 한다.(돈이 많다면, 문서를 생성하고 다운로드를 제공해주는 솔루션을..)
-> 왜 느린지 보고 싶다면, /test2 URI 를 호출해보면 된다.
