# REST API

- Base URL: `/api`
- 삭제는 전부 소프트 삭제: DELETE 요청은 실제 로우를 지우지 않고 삭제여부/삭제일시만 갱신하며, 이후 목록·상세 조회에서 제외됨

## 목록

| 기능 | Method | URL |
|---|---|---|
| 회원가입 | POST | /api/auth/signup |
| 로그인 | POST | /api/auth/login |
| 로그아웃 | POST | /api/auth/logout |
| 내 정보 조회 | GET | /api/auth/me |
| 글 목록 (커서 페이징) | GET | /api/boards |
| 글 상세 | GET | /api/boards/{id} |
| 글 등록 | POST | /api/boards |
| 글 수정 (본인만) | PATCH | /api/boards/{id} |
| 글 삭제 (본인만, soft delete) | DELETE | /api/boards/{id} |
| 댓글 목록 | GET | /api/boards/{boardId}/comments |
| 댓글 등록 (비동기) | POST | /api/boards/{boardId}/comments |
| 댓글 삭제 (본인만, 비동기, soft delete) | DELETE | /api/comments/{id} |

## 인증

### 회원가입

`POST /api/auth/signup`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| loginId | string | 로그인 아이디, 6~24자, 영문 소문자+숫자 |
| password | string | 비밀번호, 8~24자, 대문자/소문자/숫자/특수문자 중 2종류 이상 포함 |

응답

```json
{ "id": 1, "loginId": "abc123", "nickname": "알록달록한 공작새", "createdAt": "2026-09-23T09:00:00.000Z" }
```

### 로그인

`POST /api/auth/login`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| loginId | string | 로그인 아이디 |
| password | string | 비밀번호 |

응답

```json
{ "id": 1, "loginId": "abc123", "nickname": "알록달록한 공작새" }
```

### 로그아웃

`POST /api/auth/logout`

파라미터 없음

응답: `204 No Content`

### 내 정보 조회

`GET /api/auth/me`

파라미터 없음

응답

```json
{ "id": 1, "loginId": "abc123", "nickname": "알록달록한 공작새" }
```

## 게시글

### 글 목록 (커서 페이징)

`GET /api/boards`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| cursor | number (query, 선택) | 마지막으로 조회한 게시글번호. 없으면 최신글부터 조회 |
| size | number (query, 선택, 기본 10) | 조회할 개수 |

응답

```json
{
  "items": [
    { "id": 10, "title": "삼성전자 3분기 실적 어떻게 보세요?", "nickname": "알록달록한 공작새", "viewCount": 3, "createdAt": "2026-09-23T09:00:00.000Z" }
  ],
  "nextCursor": 9
}
```

### 글 상세

`GET /api/boards/{id}`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| id | number (path) | 게시글번호 |

응답

```json
{
  "id": 10,
  "title": "삼성전자 3분기 실적 어떻게 보세요?",
  "content": "...",
  "nickname": "알록달록한 공작새",
  "memberId": 1,
  "viewCount": 4,
  "createdAt": "2026-09-23T09:00:00.000Z",
  "updatedAt": "2026-09-23T09:00:00.000Z"
}
```

### 글 등록

`POST /api/boards`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| title | string (body) | 제목 |
| content | string (body) | 내용 |

응답

```json
{
  "id": 11,
  "title": "제목",
  "content": "내용",
  "nickname": "알록달록한 공작새",
  "viewCount": 0,
  "createdAt": "2026-09-23T09:00:00.000Z",
  "updatedAt": "2026-09-23T09:00:00.000Z"
}
```

### 글 수정 (본인만)

`PATCH /api/boards/{id}`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| id | number (path) | 게시글번호 |
| title | string (body) | 제목 |
| content | string (body) | 내용 |

응답

```json
{ "id": 11, "title": "수정된 제목", "content": "수정된 내용", "updatedAt": "2026-09-23T09:05:00.000Z" }
```

### 글 삭제 (본인만, soft delete)

`DELETE /api/boards/{id}`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| id | number (path) | 게시글번호 |

응답: `204 No Content`

## 댓글

### 댓글 목록

`GET /api/boards/{boardId}/comments`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| boardId | number (path) | 게시글번호 |

응답

```json
[
  { "id": 1, "content": "저는 낙관적으로 봅니다", "nickname": "황소개미", "memberId": 2, "createdAt": "2026-09-23T09:10:00.000Z" }
]
```

### 댓글 등록 (비동기)

`POST /api/boards/{boardId}/comments`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| boardId | number (path) | 게시글번호 |
| content | string (body) | 댓글 내용 |

응답

```json
{ "id": 2, "content": "댓글 내용", "nickname": "황소개미", "createdAt": "2026-09-23T09:12:00.000Z" }
```

### 댓글 삭제 (본인만, 비동기, soft delete)

`DELETE /api/comments/{id}`

| 파라미터 | 타입 | 설명 |
|---|---|---|
| id | number (path) | 댓글번호 |

응답: `204 No Content`

## 공통 에러 응답

| 상태 코드 | 설명 |
|---|---|
| 400 | 요청 형식/유효성 오류 (아이디·비밀번호 규칙 위반 등) |
| 401 | 로그인 필요 (쿠키 없음/만료) |
| 403 | 본인 소유가 아닌 글/댓글에 대한 수정·삭제 시도 |
| 404 | 대상 게시글/댓글 없음 (삭제된 경우 포함) |
| 409 | 로그인아이디 중복 등 |
