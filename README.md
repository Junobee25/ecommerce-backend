# 예약 구매 서비스

[![GitKraken shield](https://img.shields.io/badge/GitKraken-Legendary%20Git%20Tools-teal?style=plastic&logo=gitkraken)](https://gitkraken.link/Junobee25)  
멀티 서버, 쓰레드 환경에서 발생할 수 있는 동시성 이슈 고려해서 개발한 예약 구매 프로젝트 입니다.  
상품, 주문, 재고, 구매 기능이 포함된 E-COMMERCE 플랫폼에서 사용되는 기본적인 API들을 경험할 수 있도록 만들어졌습니다.

## 개발 환경
* <img src="https://img.shields.io/badge/Java-3766AB?style=flat-square&logo=Java&logoColor=white"/> version 17
* <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> version 3.2.2
* <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySql&logoColor=white"/> version 8.3.0
* <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/> version 24.0.6

## 사용 기술
* 패스워드 암호화 : Spring Security
* 인증 및 인가 : Json Web Token(JWT)
* 이메일 인증 : Google SMTP Server
* DB 엑세스 및 ORM : JPA
* REST API 통신 : Feign Client
* API 관리 : API GateWay

## 아키텍처
![image](https://github.com/Junobee25/ecommerce-backend/assets/109403631/89a25639-0b76-4123-a99d-7817a9022c1a)

## 테스트 시나리오
![image](https://github.com/Junobee25/ecommerce-backend/assets/109403631/74125b25-b3f5-4451-a7c2-da8b30c66987)

## 도커 컴포즈를 통해 각 서비스의 DB 실행
* `user-service`
* `product-service`
* `stock-service`
* `order-service`

**Example**
```
 PS C:\Users\ecommerce_project\ecommerce-backend> cd user-service
 PS C:\Users\ecommerce_project\ecommerce-backend\user-service> docker-compose up -d  
 [+] Building 0.0s (0/0)                                                                                                                                              docker:default
 [+] Running 2/2
 ✔ Network user-service_default    Created                                                                                                                                  0.1s
 ✔ Container user-service-mysql-1  Started                                                                                                                                  0.1s
 PS C:\Users\ecommerce_project\ecommerce-backend\user-service> 
```
**Result Docker Desktop**
![image](https://github.com/Junobee25/pre-order-service/assets/109403631/8bd4d291-948c-4a4a-8915-ad571952c704)


