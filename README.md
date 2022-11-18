# getbang - 겟방
- 방탈출 카페 통합 예약 시스템

# Table of contents

- [방탈출 카페 예약](#---)
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

 
# 이벤트 스토밍

- MSAEz 로 모델링한 이벤트스토밍 결과: https://www.msaez.io/#/storming/gvBKJ8asEZSiRXXDACkN51rc7D83/2ea40b104d4dea04fd51633b608fbe1e
![image](https://user-images.githubusercontent.com/20436113/200521709-fdb79870-2ef3-4dcd-b138-fc5475dae2e1.png)


- 도메인 서열 분리 
    - Core Domain:  reservation, management
    - Supporting Domain:  viewpage(ReseravationStatusView)
    

