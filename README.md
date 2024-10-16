ConcertBooking

■ STEP 05 

1. 시나리오 선정 및 프로젝트 MILESTOLE 
 1-1) 시나리오 선정 
    콘서트 예약 서비스

 1-2) MILESTONE
![ConcertBooking_milestone.jpg](ConcertBooking_milestone.jpg)
----------------------------------------------------------------------------------------

2. 시나리오 요구사항 별 분석 자료 제출

  - 콘서트 조회
  sequenceDiagram
    participant User
    participant Service

    User->>Service: 원하는 콘서트 상세 조회
    Service-->>User: 유저 토큰 발급 (순서 대기)

    loop 대기 중
     Service-->>User: 예약 가능한 순서 대기 중 메시지
    end

    User->>Service: 예약 가능 날짜 조회
    Service-->>User: 예약 가능한 날짜 출력

    User->>Service: 선택한 날짜의 예약 가능 좌석 조회
    Service-->>User: 빈 좌석 정보 출력
![SelectConcert.png](SelectConcert.png)

- 콘서트 좌석 예약
  sequenceDiagram
    participant User
    participant Service
 
    User->>Service: 좌석 예약 요청 (날짜, 좌석 정보 입력)
    Service-->>User: 좌석 임시 점유 시간 배정(5분)
![Temp_SeatBooking.png](Temp_SeatBooking.png)

- 콘서트 좌석 예약
  sequenceDiagram
    participant User
    participant Service

    User->>Service: 예약 내역 조회
      alt 5분 이내
          Service-->>User: 예약 내역 출력
      else 5분 초과
          Service-->>User: 예약 내역 미출력
      end
![SelectBookingList.png](SelectBookingList.png)  

- 잔액 조회 / 충전
  sequenceDiagram
    participant User
    participant Service

    User->>Service: 잔액 조회 요청
    Service-->>User: 잔액 및 history 출력

    User->>Service: 잔액 충전 요청
    Service-->>User: 잔액 충전 및 history 생성
![PointService.png](PointService.png)

- 콘서트 결제
  sequenceDiagram
    participant User
    participant Service

    User->>Service: 결제 요청
      alt 5분 이내
          Service-->>User: 결제 완료, 결제 history 생성, 좌석 최종 배정, 토큰 만료
      else 5분 초과
          Service-->>User: 결제 실패 (예약 시간이 만료됨)
      end
![RequestPayment.png](RequestPayment.png)


----------------------------------------------------------------------------------------

■ STEP 06

1. ERD 설계 자료 제출
 - 사용자(TB_USER) - 포인트 히스토리(TB_POINT_HISTORY): [1:N]
 - 사용자(TB_USER) - 예약 대기열(TB_QUEUE): [1:N]
 - 콘서트(TB_CONCERT) - 콘서트 장소(TB_CONCERT_PLACE): [N:1]
 - 콘서트(TB_CONCERT) - 콘서트 예약(TB_CONCERT_BOOKING): [1:N]
 - 콘서트(TB_CONCERT) - 콘서트 좌석(TB_CONCERT_SEAT): [1:N]
 - 콘서트 좌석(TB_CONCERT_SEAT) - 콘서트 예약(TB_CONCERT_BOOKING): [1:1]
 - 사용자(TB_USER) - 콘서트 예약(TB_CONCERT_BOOKING): [1:N]
![ConcertBooking_ERD.png](ConcertBooking_ERD.png)

2. API 명세 및 Mock API 작성
 - ConcertController
 - PointController
 - QueueController
 - UserController
위 파일에 작성

3. 패키지 구조 (Layered Architecture)
   io.hhplus.booking
   ├── concert
   │   ├── controller
   │   ├── model
   │   ├── repository
   │   └── service
   ├── point
   │   ├── controller
   │   ├── model
   │   ├── repository
   │   └── service
   └── queue
   ├── controller
   ├── model
   ├── repository
   └── service

4. 기술 스택
 - JWT (사용자 토큰 발행)
 - mysql (database)
 - JPA
 - lock(동시성 관리)
 - scheduler (대기열 관리)