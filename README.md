# getbang - 겟방
- 방탈출 카페 통합 예약 시스템
- 개발 계기 : 보통 방탈출 카페는 예약제로 운영된다. 하지만 예약을 위해서 소비자는 방탈출 카페의 홈페이지를 하나하나 검색해서 들어간 후 예약을 해야한다. 이러한 문제점을 해소하기 위해 소비자가 모든 방탈출 카페의 목록을 확인하고 예약도 진행할 수 있는 방탈출 카페 통합 예약 시스템을 개발하였다.
- 프로젝트 소개 : 
  1. 버전1은 프론트엔드 개발은 생략
  2. MSA 기반 설계를 적용하여 서비스 확장 및 운영에 유연하게 대응가능한 애플리케이션 구축
  3. Kafka를 이용해 각 서비스 간의 이벤트 처리
  4. 쿠버네티스/도커를 활용해 애플리케이션 구축
 
- 추후 계획 : 
  1. 프론트엔드 추가. React를 이용하여 웹앱 형태로 개발
  2. Jenkins 를 활용하여 CI/CD 파이프라인 구축
  3. EFK를 이용해 로깅 및 모니터링 기능 추가

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
    
# 애플리케이션 구조
  ![image](https://user-images.githubusercontent.com/20436113/202630142-73a58e41-4fce-4294-9ea9-cd9225da341d.png)

# 애플리케이션 구조
  
# DB 테이블 구조
  ![image](https://user-images.githubusercontent.com/20436113/202624539-e083093b-8310-4ea2-95db-be0209f5f740.png)


# 구현

- Deloyment.yaml(예시 reservation. viewpage와 management 모두 동일)
    ```
      apiVersion: apps/v1
      kind: Deployment
      metadata:
        name: reservation
        labels:
          app: reservation
      spec:
        replicas: 2
        selector:
          matchLabels:
            app: reservation
        template:
          metadata:
            labels:
              app: reservation
          spec:
            containers:
              - name: reservation
                image: ewqsaz123/reservation:latest
                ports:
                  - containerPort: 8080
     
    ```
- Service.yaml
    ```
        apiVersion: v1
        kind: Service
        metadata:
          name: reservation
          labels:
            app: reservation
        spec:
          ports:
            - port: 8080
              targetPort: 8080
          selector:
            app: reservation
    ```
- fdfd
