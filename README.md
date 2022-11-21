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
  - [서비스 시나리오](#서비스 시나리오)
  - [체크포인트](#이벤트 스토밍)
  - [애플리케이션 구조](#애플리케이션구조)
  - [DB구조](#DB구조)
  - [구현](#구현)

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
  ![image](https://user-images.githubusercontent.com/20436113/202940634-ab366e4b-3864-4cf3-b0d8-96c750fed7fd.png)


  
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
    
    
- 쿠버네티스 배포
  [네임스페이스 : service]
  
  ![image](https://user-images.githubusercontent.com/20436113/202939430-5cf6c627-d144-49d0-ac12-da22ee52f30c.png)

  [네임스페이스 : kafka]
  
  ![image](https://user-images.githubusercontent.com/20436113/202939898-a0648888-a67b-4420-b3ee-886d423161de.png)

    
- REST API 테스트
 1. 룸 생성
   - 케이스 시나리오 : 2개 룸 생성(id:1,2) -> 예약상태(viewpage) 조회 
    [Id:1번 룸 생성] - 결과 : 테이블 저장
    ![image](https://user-images.githubusercontent.com/20436113/202937818-7ea82eb9-15e7-4b67-bd98-f2c5f0a956e9.png)
    
    [ID:2번 룸 생성] - 결과 : 테이블 저장
    ![image](https://user-images.githubusercontent.com/20436113/202937887-256271cb-9fe6-4479-925d-8509078940f4.png)
    
    [예약상태(viewpage) 조회] - 결과 : viewpage 관련 테이블에 해당 룸 정보 동일하게 저장됨
    ![image](https://user-images.githubusercontent.com/20436113/202938062-6b4ecf21-6d85-4019-b529-7803863f27a8.png)

    
 2. 예약 요청
   - 케이스 시나리오 : scheduleId=2 에 대해 예약 요청 -> 룸(management) 서비스측 상태 조회, 예약상태(viewpage) 서비스측 상태 조회
   --> 생성된 룸 정보가 viewpage에도 추가됨(kakfa를 이용한 pub/sub 통신) 
    [scheduleId=1 에 대해 예약 요청] - 결과 : 테이블 저장
    ![image](https://user-images.githubusercontent.com/20436113/202938191-638c011a-ff9a-4ec3-b0a1-6605658496bc.png)
    
    [룸(management) 서비스측 상태 조회] - 결과 : Id=2인 룸의 roomStatus가 REQ(예약요청 상태)로 변경됨
    ![image](https://user-images.githubusercontent.com/20436113/202938248-741f7056-45e9-4c2a-ab93-7cec4f5278de.png)
    
    [예약상태(viewpage) 서비스측 상태 조회] - 결과 : Id=2인 룸의 추가/변경된 정보가 동일하게 반영됨
     ![image](https://user-images.githubusercontent.com/20436113/202938374-89a7506e-06ca-4dc5-8e86-57b5114d92a4.png)


 3. 예약 승인
   - 케이스 시나리오 : scheduleId=1 인 룸의 예약을 승인 -> 예약(reservation) 서비스측 조회, 예약상태(viewpage) 서비스측 상태 조회
    [scheduleId=1 인 룸의 예약을 승인] - 결과 : 테이블 update
    ![image](https://user-images.githubusercontent.com/20436113/202938447-75aa26c7-1ac7-457c-a833-d1f3ac4db411.png)
    
    [예약(reservation) 서비스측 조회] - 결과 : reservationStatus가 APR(예약승인상태)로 변경됨
    ![image](https://user-images.githubusercontent.com/20436113/202938508-24846ee0-1579-486f-af66-1e9a50fbb64a.png)
     
    [예약상태(viewpage) 서비스측 상태 조회] - 결과 : 변경된 정보가 동일하게 반영됨
    ![image](https://user-images.githubusercontent.com/20436113/202938644-7d4c80ca-01e5-4bcd-831b-708019b812aa.png)


 4. 예약 취소
   - 케이스 시나리오 : reservationId=1 인 예약을 취소 -> 룸(management) 서비스측 상태 조회, 예약상태(viewpage) 서비스측 상태 조회
    [reservationId=1 인 예약을 취소] - 결과 : 테이블 Update
    ![image](https://user-images.githubusercontent.com/20436113/202938798-f2a450a4-3112-4a6e-a96a-49b9848ff469.png)

    [룸(management) 서비스측 상태 조회] - 결과 : roomStatus가 CAN(예약취소상태)로 변경됨
    ![image](https://user-images.githubusercontent.com/20436113/202938858-f067d9fc-7387-4a8a-8da0-5ba5a830d643.png)

    [예약상태(viewpage) 서비스측 상태 조회] - 결과 : 변경된 정보가 동일하게 반영됨
    ![image](https://user-images.githubusercontent.com/20436113/202938937-3f9675a1-38cd-4408-a77d-9c5be3828d4e.png)

