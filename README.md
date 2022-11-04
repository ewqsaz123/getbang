# getbang - 겟방
- 방탈출 카페 통합 예약 시스템
- Cloud Native App

# Table of contents

- [호텔예약](#---)
  - [서비스 시나리오](#서비스-시나리오)
  - [체크포인트](#체크포인트)

# 서비스 시나리오


기능적 요구사항
1. 방탈출 카페 사장님이 룸을 생성할 수 있다.
2. 고객이 예약하려는 시간과 룸을 선택하고 예약요청을 한다.
3. 예약요청이 되면 내역이 사장님에게 전달된다
4. 사장님이 예약을 최종 승인/거절 한다
5. 사장님이 거절하면 예약이 취소된다.
6. 고객이 승인된 예약을 취소할 수 있다. (단, 예약 1시간 전까지만 취소가능)
7. 고객은 예약 현황을 조회하고, 사장님은 룸/예약 상태를 확인할 수 있다.

비기능적 요구사항
1. 장애격리
    1. 예약 관리 기능이 수행되지 않더라도 예약 주문&승인은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
2. 성능
    1. 예약 및 룸 관리기능과 별도로 이루어진 조회만을 위한 서비스가 있어야 한다.(CQRS)
    
 
# 이벤트 스토밍

- MSAEz 로 모델링한 이벤트스토밍 결과: https://www.msaez.io/#/storming/gvBKJ8asEZSiRXXDACkN51rc7D83/2ea40b104d4dea04fd51633b608fbe1e
<img width="1001" alt="image" src="https://user-images.githubusercontent.com/20436113/199900373-d6e6582e-b937-4304-82e9-f21dd340de9a.png">



