# JPA를 이용한 일정 관리 앱 제작

## 구현사항
### 문서(필수 Lv0)

#### API 명세서
  ![Screenshot 2024-10-17 at 01 29 09](https://github.com/user-attachments/assets/8112ca39-fea8-4998-a733-0e4a55247bbb)

#### ERD
  ![Screenshot 2024-10-16 at 23 42 51](https://github.com/user-attachments/assets/92a2fe47-947a-45c9-8d70-526a1d083660)

#### Sql 테이블 : 프로젝트 최상위 경로 todo.sql 파일 확인

### 일정(todo)
+ 일정 CRUD 기능 구현 (필수 Lv1)
+ 일정 삭제 시 일정의 댓글도 함께 삭제 : 영속성 전이 기능 사용 (필수 Lv1)
+ 일정 페이징 기능 구현 : 쿼리 파라미터로 페이지 번호와 크기를 전달 (필수 Lv3)
+ validation으로 예외처리 : title(1-20자 제한), contents(1-100자 제한) (필수 Lv5)
+ 일정의 수정과 삭제는 관리자 권한이 있을 때만 가능 (도전 Lv3)
+ 외부 API 조회 (도전 Lv4)
  + 날씨 필드 추가 
  + 생성일 기준의 날씨를 조회
### 댓글(comment)
+ 댓글 CRUD 기능 구현 (필수 Lv2)
+ 댓글과 일정 연관관계 설정 (필수 Lv2)
+ validation으로 예외처리 : contents(1-50자 제한) (필수 Lv5)
+ 댓글은 작성한 본인만 수정 삭제가 가능 (도전 Lv2)
### 유저(user)
+ 유저 CRUD 기능 구현 (필수 Lv4)
+ 유저 삭제 시 해당 유저의 댓글과 일정도 삭제 (필수 Lv4) 
+ 일정과 다대다 연관관계 설정, 지연로딩 활용 (필수 Lv4)
+ validation으로 예외처리 : email(email 형식(@.)), password(영문, 숫자, 특수기호 사용), username(2-6자 제한) (필수 Lv5)
+ 회원가입기능 구현 (도전 Lv1)
  + 비밀번호 암호화
  + 회원가입 시 바로 로그인 처리
+ 로그인 기능 구현(인증) (도전 Lv2)
  + 이메일과 비밀번호를 사용한 로그인 처리
  + 이메일과 비밀번호가 일치하지 않는 경우 401, 토큰이 없는 경우 400, 만료된 토큰인 경우 401을 반환
+ user의 권한 확인(인가) 처리 (도전 Lv3)
  + 관리자와 일반 사용자로 권한을 분리
  + 권한이 없는 사용자가 권한이 필요한 API에 접근시 403 에러 발생


### API 실행 테스트(POSTMAN)
https://web.postman.co/workspace/432179a0-a650-42a3-8f49-2c03de344be6/request/38576007-52410366-839c-40c6-b595-078e2dfd7662?tab=body
+ JPA TODO PROJECT Collection을 사용하시면 됩니다.
+ User folder의 user 생성 부분은 현재 사용할 수 없으며, 회원가입으로 대체되었습니다.

### 트러블슈팅
https://velog.io/@hami/%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85-JPA%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1-%EC%A0%9C%EC%9E%91

### 아쉬운 점
+ 로그아웃 기능을 추가하지 못함
+ User RUD 인가를 설정하지 못함
+ 프론트엔드 쪽을 구현하지 못함
