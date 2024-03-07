# HDJunction BE technical challenges

### 구현 내용

- 7단계 페이징 처리까지 구현을 모두 마쳤습니다.
  - spring-restdocs 라이브러리에 대한 적용은 누락되어 있습니다.
- `Spring Web`, `Spring Data JPA`, `H2 Databse`, `Querydsl` 4가지 라이브러리를 이용하였습니다.

### Git strategy

- **Trunk-based** 전략을 채택하였습니다.
- 과제의 단계별 `branch` 를 생성하였고, 한 단계가 종료될 때 마다 `Pull Request` 를 통해 `main` 브랜치에 병합하였습니다.
  - commit은 작은 작업 단위로 생성하였습니다.
- 따라서, **단계별 변경 사항** 확인은 **PR 히스토리**를 참고하여 주시기 바랍니다.

### 구현 API

- `/api/v1/patient`
  - `@Get /all` : 환자 목록 전체 조회 (Entity 내용을 그대로 반환)
  - `@Get /{id}` : 환자id를 이용한 특정 환자 조회 (Entity 내용을 그대로 반환)
  - `@Post /create` : 신규 환자 생성
    - [`PatientRequestDto`](./chart/src/main/java/com/chmun/chart/dto/patient/PatientRequestDto.java) 형식으로 전달받아 신규 환자를 생성합니다.
    - 각 병원별로 환자등록번호가 겹치지 않도록, 병원별로 `마지막 등록번호+1` 값을 서버에서 생성하여 저장합니다.
  - `@Put /update/{id}` : 환자 정보 수정
    - 수정 가능한 정보는 `이름`, `성별`, `생년월일`, `휴대전화` 로 제한하였습니다.
    - 프로그램 사용자가 다른 병원의 시스템으로 접속하는 경우는 없다고 가정하였습니다. 따라서 `병원id`는 변경 항목에서 제외하였습니다.
    - 한번 부여받은 `환자등록번호`는 변경하는 경우가 없다고 가정하였습니다.
  - `@Delete /delete/{id}` : 환자 정보 삭제
    - `Patient` 테이블에 `useYn` 플래그를 추가하였고 이 값을 업데이트 하는 방식으로 구현하였습니다.
      - 실제 데이터를 삭제하는 것은 설계적으로 부적합하다고 판단했습니다.
        - 데이터가 삭제되면, 환자의 방문 기록도 함께 삭제되거나, 연결이 깨지게 될 것입니다.
  - `@Post /list` : 전체 환자 목록 조회
    - [`PatientListRequestDto`](./chart/src/main/java/com/chmun/chart/dto/patient/PatientListRequestDto.java) 를 Request Body로 전달받습니다. `PatientListRequestDto` 에는 `병원id` 항목이 존재합니다.
    - `병원id` 값을 Query String으로 전달받으면, 사용자가 브라우저 주소창에서 해당 값을 조작하여 손쉽게 다른 병원의 데이터에 접근할 수 있을 것이므로, 이를 방지하고자 하였습니다.
  - `@Post /search` : 환자 검색
    - [`PatientListRequestDto`](./chart/src/main/java/com/chmun/chart/dto/patient/PatientListRequestDto.java) 를 Request Body로 전달받습니다.
    - `name`, `chartId`, `birthday` 인자로 환자를 검색할 수 있습니다.
    - `pageSize`, `pageNo` 인자로 페이징을 처리합니다.
- `/api/v1/visit`
  - `@Get /all` : 환자방문 기록 전체 조회 (Entity 내용을 그대로 반환)
  - `@Get /{id}` : 환자방문id를 이용한 특정 방문 기록 조회 (Entity 내용을 그대로 반환)
  - `@Post /create` : 신규 환자방문 내역 생성
    - [`VisitRequestDto`](./chart/src/main/java/com/chmun/chart/dto/patient/VisitRequestDto.java) 형식으로 전달받아 신규 환자방문 내역을 생성합니다.
  - `@Put /update/{id}` : 방문 내역 수정
    - 수정 가능한 정보는 `접수일시`, `방문상태코드` 로 제한하였습니다.
  - `@Delete /delete/{id}` : 환자방문정보 삭제
