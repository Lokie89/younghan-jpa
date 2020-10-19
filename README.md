#### 1-1 SQL 을 직접 다룰 때 발생하는 문제
###### 정리
    SQL 을 직접 다룰 땐, 객체가 연관된 다른 객체를 사용할 수 있을 지 없을지는 전적으로 사용하는 SQL 에 달려있다.
    SQL 에 모든 것을 의존하는 상황에서는 개발자들이 엔티티를 신뢰하고 사용할 수 없다.
    대신에 DAO 를 열어서 어떤 SQL 이 실행되고 어떤 객체들이 함께 조회되는지 일일이 확인해야 한다.
    SQL 과 JDBC API 를 데이터 접근 계층에 숨기는데 성공했을지는 몰라도 논리적으로는 엔티티와 아주 강항 의존관계를 가지고 있다.
    이런 강한 의존관계 때문에 회원을 조회할 때는 물론이고 회원 객체에 필드를 하나 추가할 때도 DAO 의 CRUD 코드와 SQL 대부분을
    변경해야 하는 문제가 발생한다.
    
    JPA 를 사용하면 객체를 데이터베이스에 저장하고 관리할 때, 개발자가 직접 SQL 을 작성하는 것이 아니라
    JPA 가 제공하는 API 를 사용하면 된다.
    JPA 가 개발자 대신 적절한 SQL 을 생성하여 데이터베이스에 전달한다.
    
    기능 :
        persist( 객체 ); : 데이터베이스에 저장
        find( class명, 아이디 ); : 객체 하나를 조회
        set~ : JPA 는 별도의 수정 메서드를 제공하지 않음, 조회 후 값을 변경하면 커밋할때 자동으로 update 됨
        get~ : 연관된 객체 조회, 메서드를 실행하는 시점에 연관 객체를 조회한다.

#### 1-2 패러다임의 불일치
###### 정리
    애플리케이션은 자바라는 객체지향 언어로 개발하고 데이터는 관계형 데이터베이스에 저장해야 한다면,
    패러다임의 불일치 문제를 개발자가 중간에서 해결해야 한다.
    상속
        JPA 는 상속과 관련된 패러다임의 불일치 문제를 개발자 대신 해결해준다.
        개발자는 마치 자바 컬렉션에 객체를 저장하듯이 JPA 에게 객체를 저장하면 된다.
    연관관계
        객체는 참조를 사용해서 다른 객체와 연관관계를 가지고 참조에 접근해서 연관된 객체를 조회한다.
        반면에 테이블은 외래 키를 사용해서 다른 테이블과 연관관계를 가지고 조인을 사용하여 연관된 테이블을 조회한다.
        개발자는 회원과 팀의 관계를 설정하고 회원 객체를 저장하면 된다.
        JPA 는 객체의 참조를 외래 키로 변환해서 적절한 INSERT SQL 을 데이터 베이스에 전달한다.
        객체를 조회할 때 외래 키를 참조로 변환하는 일도 JPA 가 해준다.
    객체 그래프 탐색
        SQL 을 직접 다루면 처음 실행하는 SQL 에 따라 객체 그래프를 어디까지 탑색할 수 있는지 정해진다.
        JPA 는 연관된 객체를 사용하는 시점에 적절한 SELECT SQL 을 실행한다.
        따라서 JPA 를 사용하면 연관된 객체를 신뢰하고 마음껏 조회할 수 있다.
        이 기능은 실제 객체를 사용하는 시점까지 데이터베이스 조회를 미룬다고 해서 지연 로딩이라 한다.
    객체 비교
        SQL 로 같은 데이터를 조회하여 객체에 저장한다면
        그 객체는 동등성 ( equals ) 는 갖지만 동일성 ( === ) 은 갖지 못한다.
        JPA 는 같은 트랜잭션일 때 같은 객체가 조회되는 것을 보장한다.

#### 1-3 JPA 란 무엇인가?
###### 정리
    ORM ( Object Relational Mapping ) 은 이름 그대로 객체와 관계형 데이터베이스를 매핑한다는 뜻이다.
    객체와 테이블을 매핑해서 패러다임의 불일치 문제를 개발자 대신 해결해준다.
    객체 측면에서는 정교한 객체 모델링을 할 수 있고 관계형 데이터베이스는 데이터베이스에 맞도록 모델링하면 된다.
    
    JPA 는 자바 ORM 기술에 대한 표준 명세다. 쉽게 이야기해서 인터페이스를 모아둔 것이다.
    JPA 를 사용해야 하는 이유
    생산성
        JPA 를 사용하면 자바 컬렉션에 객체를 저장하듯이 JPA 에게 저장할 객체를 전달하면 된다.
        INSERT SQL 을 작성하고 JDBC API 를 사용하는 지루하고 반복적인 일은 JPA 가 대신 처리해준다.
    유지보수
        엔티티에 필드를 하나만 추가해도 JPA 가 대신 처리해주므로 필드를 추가하거나 삭제해도 수정해야 할 코드가 줄어든다.
    패러다임의 불일치 해결
        JPA 는 상속, 연관관계, 객체 그래프 탐색, 비교하기와 같은 패러다임의 불일치 문제를 해결해준다.
    성능
        JPA 는 애플리케이션과 데이터베이스 사이에서 다양한 성능 최적화 기회를 제공한다.
    데이터 접근 추상화와 벤더 독립성
        애플리케이션과 데이터베이스 사이에 추상화된 데이터 접근 계층을 제공해서 애플리케이션이 특정 데이터베이스 기술에 종속되지 않도록 한다. 
        
#### 2 JPA 시작
###### 코드 추가
    1. 데이터베이스 테이블과 매핑할 클래스 Member 생성
    2. 로직을 실행할 JpaMain 클래스
    3. jpa 설정을 담은 META-INF 디렉토리 안 persistence.xml 파일 생성 ( 자동으로 인식 됨 )
###### maven 추가
    // JPA, Hibernate
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>4.3.10.Final</version>
    </dependency>
    // H2 데이터베이스
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>1.4.187</version>
    </dependency>
###### 정리
    엔티티 클래스 사용
    @Entity - 해당 클래스는 테이블과 매핑한다고 JPA 에게 알려줌
    @Table - 엔티티 클래스에 매핑할 테이블 정보를 알려줌
    @Id - 엔티티 클래스의 필드를 테이블의 기본 키에 매핑
    @Column - 필드를 컬럼에 매핑
    매핑 정보가 없는 필드는 어노테이션 생략 시 필드명을 사용해서 컬럼명에 매핑
    
    SQL 표준을 지키지 않거나 특정 데이터베이스만의 고유한 기능을 JPA 에서는 방언 ( Dialect ) 이라 함
    하이버네이트를 포함한 대부분의 JPA 구현체들은 이런 문제를 해결하려고 다양한 데이터베이스 방언 클래스를 제공함
    
    엔티티 매니저 설정
    1. 엔티티 매니저 팩토리 생성
        엔티티 설정파일을 읽어 해당 persistenct-unit 을 찾아 객체를 만들고 데이터 베이스 커넥션 풀도 생성하므로
        엔티티 매니저 팩토리를 생성하는 비용은 아주 크다.
        따라서 엔티티 매니저 팩토리는 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 한다.
    2. 엔티티 매니저 생성
    생성된 엔티티 매니저 팩토리에서 엔티티 매니저를 생성한다.
    엔티티 매니저는 엔티티를 데이터베이스에 등록 / 수정 / 삭제 / 조회 할 수 있다.
    엔티티 매니저는 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용하면 안된다.
    3. 종료
        사용이 끝나면 엔티티 매니저를 종료하고, 애플리케이션이 종료되면 앤티티 매니저 팩토리도 종료해야 한다.
    
    JPA 를 사용하면 항상 트랜잭션 안에서 데이터를 변경해야 한다.
    JPA 는 엔티티의 매핑 정보를 분석해서 SQL 을 만들어 데이터베이스에 전달한다.
    JPA 는 어떤 엔티티가 변경되었는지 추적하는 기능을 가지고 있다.
    엔티티의 값만 변경하면 데이터베이스의 값을 변경한다.
    
    JPA 를 사용하면 애플리케이션 개발자는 엔티티 객체를 중심으로 개발하고 데이터 베이스에 대한 처리는 JPA 에 맡겨야 한다.
    그런데 테이블이 아닌 엔티티 객체를 대상으로 검색하려면 데이터베이스의 모든 데이터를 애플리케이션으로 불러와서
    엔티티 객체로 변경한 다음 검색해야 하는데, 이는 사실상 불가능하다. 애플리케이션이 필요한 데이터만 데이터베이스에서
    불러오려면 결국 검색 조건이 포함된 SQL 을 사용해야 한다.
    JPA 는 JPQL ( Java Persistence Query Language ) 라는 쿼리 언어로 이런 문제를 해결한다.
    SQL 과 SQL 을 추상화 한 JPQL 의 차이점은 JPQL 은 엔티티 객체를 대상으로 SQL 은 데이터베이스 테이블을 대상으로 쿼리하는 것이다.
    
#### 3-1 엔티티 매니저 팩토리와 엔티티 매니저
###### 정리
    엔티티 매니저 팩토리는 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드 간에 공유해도 되지만,
    엔티티 매니저는 여러 스레드가 동시에 접근하면 동시성 문제가 발생하므로 스레드 간에 절대 공유하면 안 된다.
    
    엔티티 매니저는 데이터 베이스 연결이 꼭 필요한 시점까지 커넥션을 얻지 않는다.

#### 3-2 영속성 컨텍스트란?
###### 정리
    엔티티를 영구 저장하는 환경
    엔티티 매니저로 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
    엔티티 매니저를 사용해서 엔티티를 영속성 컨텍스트에 저장한다.
    영속성 컨텍스트는 엔티티 매니저를 생성할 때 하나 만들어진다.
    
#### 3-3 엔티티의 생명주기
###### 정리
    비영속 ( new / transient ) : 영속성 컨텍스트와 전혀 관계가 없는 상태
    영속 ( managed ) : 영속성 컨텍스트에 저장된 상태
    준영속 ( detached ) : 영속성 컨텍스트에 저장되었다가 분리된 상태
    삭제 ( removed ) : 삭제된 상태

#### 3-4 영속성 컨텍스트의 특징
###### 정리
    영속성 컨텍스트는 엔티티를 식별자 값으로 구분한다. 따라서 영속 상태는 식별자 값이 반드시 있어야 한다.
    JPA 는 보통 트랜잭션을 커밋하는 순간 영속성 컨텍스트에 새로 저장된 엔티티를 데이터베이스에 반영한다.
    
    영속성 컨텍스트의 장점
        a. 1차 캐시
            영속성 컨텍스트는 내부에 캐시를 가지고 있는데 엔티티는 모두 이곳에 저장된다.
            1차 캐시의 키는 식별자 값이다. 그리고 식별자 값은 데이터베이스 기본 키와 매핑되어 있다.
            영속성 컨텍스트에 데이터를 저장하고 조회하는 모든 기준은 데이터베이스 기본 키 값이다.
            find() 메서드를 호출하면 먼저 1차 캐시에서 엔티티를 찾고 만약 없다면 데이터베이스를 조회한다.
        b. 동일성 보장
            데이터 조회 시 영속성 컨텍스트는 1차 캐시에 있는 엔티티 인스턴스를 반환하기 때문에 엔티티의 동일성을 보장한다.
        c. 트랜잭션을 지원하는 쓰기 지연
            엔티티 매니저는 트랜잭션을 커밋하기 전까지 데이터베이스에 엔티티를 저장하지 않고
            내부 쿼리 저장소에 SQL 을 모아둔다.
            트랜잭션을 커밋할 때 모아둔 쿼리를 데이터 베이스에 보내는데 이것을 트랜잭션을 지원하는 쓰기 지연이라 한다.
            트랜잭션을 커밋하면 엔티티 매니저는 우선 영속성 컨텍스트를 플러시한다.
            플러시는 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 작업인데 이때 등록, 수정, 삭제 된 엔티티를 데이터베이스에 반영한다.
        d. 변경 감지
            JPA 로 엔티티를 수정할 때는 단순히 엔티티를 조회해서 데이터만 변경하면 된다.
            엔티티의 변경사항을 데이터베이스에 자동으로 반영하는 기능을 변경 감지라 한다.
            JPA 는 엔티티를 영속성 컨텍스트에 보관할 때, 최초 상태를 복사해서 저장해주는데 이것을 스냅샷이라 한다.
            트랜잭션을 커밋하면 엔티티와 스냅샷을 비교해서 변경된 엔티티를 찾고 수정 쿼리를 생성해서 SQL 에 저장한다.
            필드가 많거나 저장되는 내용이 너무 크면 DynamicUpdate 를 사용하여 동적 SQL 을 작성하는 편이 좋다.
            엔티티를 삭제할 경우 remove 를 호출하는 순간 영속성 컨텍스트에서 제거된다.
            삭제된 엔티티는 재사용하지 말고 자연스럽게 가비지 컬렉션의 대상이 되도록 두는 것이 좋다.
            
#### 3-5 플러시
###### 정리
    플러스 ( flush ) 는 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다.
        - 변경 감지가 동작해서 스냅샷과 비교해 수정된 엔티티를 찾는다.
        - 수정 쿼리를 만드러 쓰기 지연 SQL 저장소에 등록한다.
        - 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송한다.
        
    영속성 컨텍스트를 플러시하는 방법
        - 직접 호출 : flush() 메서드를 직접 호출
        - 트랜잭션 커밋 시 플러스 자동 호출 : JPA 는 트랜잭션을 커밋할 때 플러시를 자동으로 호출
        - JPQL 쿼리 실행 시 플러시 자동 호출 : 쿼리를 실행하기 직전에 영속성 컨텍스트를 플러시해서 변경 내용을 데이터베이스에 반영
        
    플러시라는 이름으로 인해 영속성 컨텍스트에 보관된 엔티티를 지운다고 생각하면 안된다.
    영속성 컨텍스트의 변경 내용을 데이터 베이스에 동기화하는 것이 플러시다. 

#### 3-6 준영속
###### 코드 추가
    1. 준영속 예제 DetachMain 추가
    2. 병합 예제 MergeMain 추가
###### 정리
    준영속 상태의 엔티티는 영속성 컨텍스트가 제공하는 기능을 사용할 수 없다.
    
    영속 상태인 엔티티를 준영속 상태로 변경하는 방법
    
    EntityManager.detach(Entity);
        영속 상태였다가 더는 영속성 컨텍스트가 관리하지 않는 상태를 준영속 상태라 한다.
        영속 상태가 managed 되는 상태라면 준영속 상태는 detached 된 상태이다.
    EntityManager.clear();
        영속성 컨텍스트를 초기화해서 해당 영속성 컨텍스트의 모든 엔티티를 준영속 상태로 만든다.
    EntityManager.close();
        영속성 컨텍스트를 종료하면 해당 영속성 컨텍스트가 관리하던 엔티티가 모두 준영속 상태가 된다.
        
    준영속 상태는 식별자 값을 가지고 있다.
    
    준영속 상태의 엔티티를 다시 영속 상태로 변경하려면 병합을 사용한다.
    EntityManager.merge(Entity) 준영속 상태의 엔티티를 받아서 그 정보로 새로운 영속 상태의 엔티티를 반환한다.
    
    병합은 파라미터로 넘어온 엔티티의 식별자 값으로 영속성 컨텍스트를 조회하고
    찾는 엔티티가 없으면 데이터베이스에서 조회한다.
    데이터베이스에도 없으면 새로운 엔티티를 생성해서 병합한다.
    병합은 준영속, 비영속을 신경쓰지 않고 식별자 값으로 엔티티를 조회할 수 있으면 병학, 없으면 새로 생성해서 병합한다.

#### 4-1 @Entity
###### 정리
    JPA 를 사용해서 테이블과 매핑할 클래스는 @Entity 어노테이션ㄴ을 필수로 분여야 한다.
    @Entity 속성 중 name 속성은 JPA 에서 사용할 엔티티 이름을 지정하는데 지정하지 않으면 자동으로 클래스 이름을 사용한다.
    
    @Entity 적용 시 주의사항
        - 기본 생성자는 필수
        - final 클래스, enum, interface, inner 클래스에는 사용할 수 없다.
        - 저장할 필드에 final 을 사용하면 안 된다.

#### 4-2 @Table
###### 정리
    @Table 은 엔티티와 매핑할 테이블을 지정한다.
    속성 중 name 은 매핑할 테이블 이름을 지정하는데 지정하지 않으면 엔티티 이름을 사용한다.
    catalog : catalog 기능이 있는 데이터베이스에서 catalog 를 매핑
    schema : schema 기능이 있는 데이터베이스에서 schema 를 매핑
    uniqueConstraints (DDL) : DDL 생성 시에 유니크 제약 조건을 만든다.
    2개 이상의 복합 유니크 제약조건도 만들 수 있다. 참고로 이 기능은 스키마
    자동 생성 기능을 사용해서 DDL 을 만들 때만 사용된다.
    
#### 4-3 다양한 매핑 사용
###### 코드 추가
    1. Member 클래스 필드 추가
    2. RolyType enum 추가
###### 정리
    자바의 enum 을 필드로 사용하려면 @Enumerated 어노테이션으로 매핑해야함
    자바의 날짜 타입은 @Temporal 어노테이션으로 매핑해야 함
    필드에 길이 제한이 없을 경우 데이터베이스의 VARCHAR 대신 CLOB 타입을 저장해야 함, @Lob 어노테이션으로 매핑해야함

#### 4-4 데이터베이스 스키마 자동 생성
###### 정리
    클래스의 매핑 정보를 보면 어떤 테이블에 어떤 컬럼을 사용하는지 알 수 있다.
    JPA 는 이 매핑정보와 데이터베이스 방언을 사용해서 데이터베이스 스키마를 생성한다.
    
    persistence.xml 추가
    <property name="hibernate.hbm2ddl.auto" value="create"/>
    
    이 속성을 추가 하면 애플리케이션 실행 시점에 데이터베이스 테이블을 자동으로 생성한다.
    
    스키마 자동 생성 기능을 사용하면 애플리케이션 실행 시점에 데이터베이스 테이블이 자동으로 생성되므로
    개발자가 테이블을 직접 생성하는 수고를 덜 수 있다.
    하지만 스키마 자동 생성 기능이 만든 DDL 은 운영 환경에서 사용할 만큼 완벽하지는 않으므로
    개발 환경에서 사용하거나 매핑을 어떻게 해야 하는지 참고하는 정도로만 사용하는 것이 좋다.
    
    hibernate.hbm2ddl.auto 속성
    create : DROP + CREATE
    create-drop : DROP + CREATE + DROP ( 애플리케이션 종료 시 )
    update : 변경 사항 수정
    validate : 테이블 비교하여 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않음
    none : 자동생성 기능을 사용하지 않음.
    
    RoleType 처럼 단어 ( Role ) 와 단어 ( Type ) 를 구분할 때
    자바는 카멜 케이스 roleType 이며 데이터 베이스는 스네이크 케이스 role_type 를 관례적으로 사용한다.
    
    <property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.ImprovedNamingStrategy"/>
    
    추가하면 테이블 명이나 컬럼명이 생략된 필드를 언더스코어 표기법 ( 스네이크 케이스 )으로 매핑한다.
    
#### 4-5 DDL 생성 기능
###### 코드 추가
    1. Member 클래스 username 필드에 nullable length 추가
    2. Member 클래스 @Table 어노테이션에 uniqueConstraints 속성 추가
###### 정리
    @Column 어노테이션 속성 중 nullable, length 속성으로 not null, 문자 크기 를 지정할 수 있음
    이런 기능들은 단지 DDL 을 자동 생성할 때만 사용되고 JPA 의 실행 로직에는 영향을 주지 않는다.
    
#### 4-6 기본 키 매핑
###### 정리
    기본 키를 생성하는 방법
        1. 직접 할당
            persist 전에 직접 id 값을 넣어준다.
        2. IDENTITY 전략 
            엔티티가 영속 상태가 되려면 id 값이 필수다. 그러나 IDENTITY 전략은 데이터베이스에 저장해야 ID 값이 생성되므로,
            IDENTITY 전략을 사용했을 때는 em.persist() 를 호출 하는 즉시 SQL 이 전달된다.
        3. SEQUENCE 전략
            데이터 베이스의 시퀀스를 사용하여 기본 키 생성
            오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용할 수 있다.
            이 전략은 사용 전 데이터 시퀀스를 만들어야 한다.
        4. TABLE 전략
            키 생성 전용 테이블을 만들고 데이터 베이스 시퀀스를 흉내내는 전략
        5. AUTO 전략
            데이터베이스 방언에 따라 IDENTITY, SEQUENCE, TABLE 전략 중 하나를 자동으로 선택
            
#### 4-7 필드와 컬럼 매핑 레퍼런스
###### 정리
    @Column : 객체 필드를 테이블 컬럼에 매핑
    @Enumerated : enum 타입을 매핑
    @Temporal : 날짜 타입을 매핑
    @Lob : 데이터베이스 BLOB, CLOB 타입과 매핑
    @Transient : 이 필드는 매핑하지 않음
    @Access : JPA 가 엔티티 데이터에 접근하는 방식을 지정

#### 5.1 단방향 연관관계
###### 정리
    단방향 연관관계 : 둘 중 한 쪽만 참조하는 것
    회원과 팀 사이의 단방향 연관관계
        회원은 하나의 팀에만 소속
        회원과 팀은 다대일 관계
    
    객체 관점
        회원 객체는 팀 객체를 필드로 갖는다.
        회원 객체를 조회하여 팀 객체를 확인할 수 있지만,
        팀 객체를 조회하여 회원 객체를 확인할 수 없다.
        이는 단방향 관계다.
    
    테이블 관점
        회원 테이블은 외래 키로 팀 테이블과 연관관계를 갖는다.
        회원 테이블의 팀 외래 키를 통해서 팀 테이블과 조인 할 수 있고,
        반대로 팀과 회원 테이블도 조인할 수 있다.
        이는 양방향 관계다.
        e.g ) select * from member m join team t on m.team_id = t.team_id;
        select * from team t join member m on t.team_id = m.team_id;
        
    객체는 참조로 연관관계를 맺고, 테이블은 외래 키로 연관관계를 맺는다.
        
    @ManyToOne : 다대일 관계라는 매핑 정보를 뜻함
    @JoinColumn : 외래 키를 매핑

#### 5-2 연관관계 사용
###### 정리
    연관관계가 있는 엔티티 조회
        - 객체 그래프 탐색
            e.g ) member.getTeam() 을 사용해서 member 와 연관된 team 엔티티를 조회
        - 객체지향 쿼리 사용
            jpql 를 통하여 데이터 가져옴
    
    연관관계가 있는 엔티티 수정
        - update 같은 메서드는 없다. 불러온 엔티티의 값만 수정하면 커밋할 때 자동으로 반영
        
    연관관계가 있는 엔티티 연관관계 제거
        - 엔티티의 값을 null 로 수정

    연관관계가 있는 엔티티 삭제
        - 기존에 있던 연관관계를 먼저 제거 하고 remove로 삭제
        
#### 5-3 양방향 연관관계
###### 정리
    일대다 관계를 매핑하기 위해 OneToMany 사용
    이 때 mappedby 속성은 양방향 매핑일 때 사용하는데 반대쪽 매핑의 필드 이름을 값으로 주면 됨

#### 1-1
###### maven 추가
###### 정리