스파르타 코딩클럽 내일 배움 캠프 Spring 7기 심화과정 프로젝트 진행.

유저가 가진 금액을 환율 데이터를 통해 환전 해주는 API 제작을 목표로 진행 되었다.

# Tech Stack

---

JAVA : JDK 17

Spring Boot : 3.3.5

IDE : IntelliJ

MySQL : Ver 9.1.0

# API 명세서(필수)

---

| 기능             | http method | endpoint             | request  | response | 상태코드       | 예외처리                            |
|----------------|-------------|----------------------|----------|----------|------------|---------------------------------|
| 환전 요청          | POST        | /exchanges           | 요청 body  | 등록 정보    | 201: 정상 등록 | 400: 필수값 누락                     |
| 선택 고객 환전 조회    | GET         | /exchanges/{user_id} | 요청 param | 다건 응답 정보 | 200: 정상 조회 | 400: param 누락, 404: 없는 user_id  |
| 환전 요청 상태 수정    | PATCH       | /exchanges/{id}      | 요청 body  | 수정 정보    | 200: 정상 수정 | 400: 필수값 누락, 404: 없는 id         |
| 고객 삭제          | DELETE      | /users/{user_id}     | 요청 param | X        | 200: 정상 삭제 | 401: 본인이 아닌 경우, 404: 없는 user_id | 
### POST /exchanges

```java
// request
{
	"id": 1,
	"userId" : 1,
	"currentId" : 1,
	"amountInKrw": 10000,
	"amountAfterExchange" : 6.99,
	"status" : "nomal"
}

//response
201: CREATED
{
	"status": "success",
	"statusCode": 201,
	"data": {
		"id": 1,
		"userId" : 1,
		"currentId" : 1,
		"amountInKrw": 10000,
		"amountAfterExchange" : 6.99,
		"status" : "nomal",
		"createdAt" : "0000-00-00T00:00:00",
		"modifiedAt" : "0000-00-00T00:00:00"
	}
}

400: BAD REQUEST
{
	"status": "error",
	"statusCode": 400,
	"error": {
		"code": "BAD REQUEST",
		"message": "필수 값이 모두 입력되지 않았습니다.",
		"details": "[환전 전 금액 || 환전 후 금액 || 상태값]을 입력해주세요.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/exchanges"
	}
}
```

### GET /exchanges/{user_id}

```java
//response
200: OK
{
	"status": "success",
	"statusCode": 200,
	"data": [
		{
			"id": 1,
			"userId" : 1,
			"currentId" : 1,
			"amountInKrw": 10000,
			"amountAfterExchange" : 6.99,
			"status" : "nomal",
			"createdAt" : "0000-00-00T00:00:00",
			"modifiedAt" : "0000-00-00T00:00:00"
		},
		{
			"id": 2,
			"userId" : 1,
			"currentId" : 1,
			"amountInKrw": 20000,
			"amountAfterExchange" : 13.98,
			"status" : "nomal",
			"createdAt" : "0000-00-00T00:00:00",
			"modifiedAt" : "0000-00-00T00:00:00"
		},
		...
	]
}

400: BAD REQUEST
{
	"status": "error",
	"statusCode": 400,
	"error": {
		"code": "BAD REQUEST",
		"message": "request parameter를 확인해 주세요.",
		"details": "검색할 유저 아이디를 입력해 주세요.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/exchanges/"
	}
}

404: NOT FOUND
{
	"status": "error",
	"statusCode": 404,
	"error": {
		"code": "NOT FOUND",
		"message": "request parameter를 확인해 주세요.",
		"details": "없는 유저입니다.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/exchanges/5"
	}
}
```

### PATCH /exchanges/{id}

```java
// request
{
	"status" : "[nomal || cancelled]"
}

// response
200: OK
{
	"status": "success",
	"statusCode": 200,
	"data": {
		"id": 1,
		"userId" : 1,
		"currentId" : 1,
		"amountInKrw": 10000,
		"amountAfterExchange" : 6.99,
		"status" : "cancelled",
		"createdAt" : "0000-00-00T00:00:00",
		"modifiedAt" : "0000-00-00T00:00:00"
	}
}

400: BAD REQUEST
{
	"status": "error",
	"statusCode": 400,
	"error": {
		"code": "BAD REQUEST",
		"message": "request parameter를 확인해 주세요.",
		"details": "수정할 환전 번호를 입력해 주세요.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/exchanges/"
	}
}

404: NOT FOUND
{
	"status": "error",
	"statusCode": 404,
	"error": {
		"code": "NOT FOUND",
		"message": "request parameter를 확인해 주세요.",
		"details": "없는 환전 정보입니다.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/exchanges/1"
	}
}
```

### DELETE /users/{user_id}

```java
// response
200: OK
{
	"status": "success",
	"statusCode": 200
}

401: BAD REQUEST
{
	"status": "error",
	"statusCode": 400,
	"error": {
		"code": "BAD REQUEST",
		"message": "request parameter를 확인해 주세요.",
		"details": "삭제할 유저를 입력해 주세요.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/users/"
	}
}

404: NOT FOUND
{
	"status": "error",
	"statusCode": 404,
	"error": {
		"code": "NOT FOUND",
		"message": "request parameter를 확인해 주세요.",
		"details": "없는 유저 입니다.",
		"timestamp": "0000-00-00T00:00:00",
		"path": "/api/v1/users/5"
	}
}
```

# ERD

---

![스크린샷 2024-11-27 오후 3 58 47](https://github.com/user-attachments/assets/9f30f856-106e-482c-be28-cadd846b4edc)
