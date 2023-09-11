-- 테이블 삭제
DROP TABLE USER_INFO CASCADE CONSTRAINT;   -- 유저 정보 
DROP TABLE BLOCK_USERS CASCADE CONSTRAINT;     -- 차단 유저
DROP TABLE BAN_INFO CASCADE CONSTRAINT;        -- 정지 정보
DROP TABLE USER_LIKE CASCADE CONSTRAINT;       -- 유저 좋아요
DROP TABLE POST CASCADE CONSTRAINT; -- 게시글 TABLE DROP
DROP TABLE BOARD CASCADE CONSTRAINT; -- 게시판 TABLE  DROP
DROP TABLE POST_THEMA CASCADE CONSTRAINT; -- 말머리 TABLE DROP
DROP TABLE POST_LIKE CASCADE CONSTRAINT; -- 게시물 좋아요 TABLE  DROP
DROP TABLE MATCHING CASCADE CONSTRAINT;        -- 매칭 
DROP TABLE MATCHING_USER_INFO CASCADE CONSTRAINT;   -- 매칭 참여 유저 정보
DROP TABLE CHATROOM CASCADE CONSTRAINT;            -- 채팅방
DROP TABLE CHAT_INFO CASCADE CONSTRAINT;       -- 채팅 발송 정보
DROP TABLE CHAT_USER_INFO CASCADE CONSTRAINT;  -- 채팅 참여 유저 정보
DROP TABLE COMMENT_LIKE CASCADE CONSTRAINT; -- 댓글좋아요
DROP TABLE CATEGORY CASCADE CONSTRAINT;     -- 카테고리
DROP TABLE COMMENTS CASCADE CONSTRAINT;     -- 댓글
DROP TABLE CATEGORY_TYPE CASCADE CONSTRAINT; -- 카테고리(관심사)분류
DROP TABLE PLACE CASCADE CONSTRAINT;        -- 지역
DROP TABLE PLACE_TYPE CASCADE CONSTRAINT;   -- 지역 분류

-- 유저 정보 테이블
CREATE TABLE USER_INFO(
    USER_ID VARCHAR2(50) PRIMARY KEY,       -- 유저 아이디
    USER_PWD VARCHAR2(50) NOT NULL,     -- 유저 패스워드
    USER_NAME VARCHAR2(30) NOT NULL,        -- 유저 이름
    USER_NICKNAME VARCHAR2(50) UNIQUE NOT NULL,     -- 유저 닉네임
    AGE NUMBER,     -- 나이
    GENDER VARCHAR2(1) NOT NULL CHECK(GENDER IN ('남', '여')),       -- 성별
    PLACE VARCHAR2(300) NOT NULL,       -- 활동지역
    PHONE VARCHAR2(20) NOT NULL,        -- 전화번호
    EMAIL VARCHAR2(50) NOT NULL UNIQUE, -- 이메일
    PROFILE_IMG VARCHAR2(500),      -- 프로필 이미지
    STATUS_MESSAGE VARCHAR2(1000),      -- 상태 메세지
    LOVER VARCHAR2(1) CHECK(LOVER IN ('Y', 'N')),      -- 애인 유무
    BLOOD_TYPE VARCHAR2(5) CHECK(BLOOD_TYPE IN ('A', 'B', 'AB', 'O')),      -- 혈액형
    MBTI VARCHAR2(8),       -- MBTI
    BIRTHDAY DATE,       -- 생일
    USER_SUBSCRIPTION DATE DEFAULT SYSDATE NOT NULL,        -- 가입날짜
    USER_LIKES NUMBER DEFAULT 0, -- (좋아요받은 수)
    USER_RATING NUMBER, -- 유저 평점(리뷰에서 받은 평점의 평균)
    USER_ADDMIN VARCHAR2(1) DEFAULT 'N' NOT NULL CHECK(USER_ADDMIN IN ('Y', 'N')),      -- 관리자 권한 유무    
    WITHDRAWAL VARCHAR2(1) DEFAULT 'N' NOT NULL CHECK(WITHDRAWAL IN ('Y', 'N'))      -- 탈퇴 유무    
);


-- 차단 유저 정보
CREATE TABLE BLOCK_USERS( 
    BLOCK_USER_SEQ NUMBER PRIMARY KEY,     -- 유저 차단 SEQ
    USER_ID VARCHAR2(50) NOT NULL,  -- 유저 ID
    BLOCK_ID VARCHAR2(50) NOT NULL,     -- 차단 유저 ID
    BLOCK_DATE DATE DEFAULT SYSDATE NOT NULL,    -- 차단 일자 
    BLOCK_REASON VARCHAR2(100),     -- 차단 이유
    UNBLOCK VARCHAR2(1) DEFAULT 'N' NOT NULL CHECK(UNBLOCK IN ('Y', 'N'))  -- 차단 해제
);

-- 유저 정지 정보 테이블
CREATE TABLE BAN_INFO( 
    BAN_INFO_SEQ NUMBER PRIMARY KEY,    -- 정지 정보 SEQ
    BAN_ID VARCHAR2(50) NOT NULL,   -- 정지 ID
    BAN_START DATE DEFAULT SYSDATE NOT NULL,    -- 정지 시작 날짜
    BAN_END DATE NOT NULL,      -- 정지 종료 날짜
    BAN_REASON VARCHAR2(1000) NOT NULL      -- 정지 이유
);

-- 유저 인기도 테이블
CREATE TABLE USER_LIKE( 
    LIKE_UP_SEQ NUMBER PRIMARY KEY,     --  인기도 SEQ
    LIKE_UP_DATE DATE DEFAULT SYSDATE NOT NULL,     -- 인기도UP 누른 날짜
    LIKE_UP_USER VARCHAR(50) NOT NULL,      -- UP 눌러준 ID
    LIKE_UP_TARGET VARCHAR(50) NOT NULL   -- UP 눌려진 ID
);

-- 게시글 테이블
CREATE TABLE POST( 
    POST_SEQ NUMBER PRIMARY KEY,
    POST_TITLE VARCHAR2(200) NOT NULL, -- 게시글 제목
    POST_CONTENT VARCHAR2(4000) NOT NULL, -- 게시글 내용
    POST_DATE DATE DEFAULT SYSDATE NOT NULL, -- 게시글 작성 시간
    POST_VIEW NUMBER DEFAULT 0 NOT NULL, -- 조회수
    POST_ATTACHMENT VARCHAR2(4000), -- 게시글 첨부파일
    POST_URL VARCHAR2(4000) NOT NULL UNIQUE, -- 게시글 주소
    POST_USER_ID VARCHAR2(50) NOT NULL,      -- 게시글 작성자 ID
    CATEGORY_SEQ NUMBER, -- 카테고리/관심사 SEQ(매칭글일 경우에만 필요)
    PLACE_SEQ NUMBER, -- 지역 SEQ(매칭글일 경우에만 필요)
    POST_THEMA_SEQ NUMBER,  -- 말머리 SEQ(일반게시글일 경우에만 필요)
    BOARD_SEQ NUMBER,    -- 게시판 SEQ (매칭글인지 일반게시글인지 리뷰글인지 구분)
    MATCHING_SEQ NUMBER, -- 매칭SEQ (리뷰글일 경우에만 필요)
    POST_NOTICE VARCHAR2(1) DEFAULT 'N' CHECK(POST_NOTICE IN ('Y','N')), -- 공지사항(Y/N) 
    MATCHING_ACCEPT VARCHAR2(1) DEFAULT 'N' CHECK(MATCHING_ACCEPT IN ('Y','N')), -- 매칭승락(Y/N) (매칭글일 경우에만 필요)
    POST_DELETE VARCHAR2(1) DEFAULT 'N' CHECK(POST_DELETE IN ('Y','N')) -- 글 삭제 여부  
);

-- 게시판
CREATE TABLE BOARD( 
    BOARD_SEQ NUMBER PRIMARY KEY,
    BOARD_NAME VARCHAR2(100) NOT NULL UNIQUE, -- 게시판 이름
    BOARD_MANAGER VARCHAR2(1) DEFAULT 'N' CHECK(BOARD_MANAGER IN ('Y','N')) -- 관리자 전용(Y/N)
);

-- 말머리 테이블
CREATE TABLE POST_THEMA(
    POST_THEMA_SEQ NUMBER PRIMARY KEY,
    PT_NAME VARCHAR2(100) NOT NULL UNIQUE -- 말머리 제목
);

-- 게시글 좋아요
CREATE TABLE POST_LIKE(
    POST_LIKE_SEQ NUMBER PRIMARY KEY,   -- 게시글 좋아요 SEQ
    POST_SEQ NUMBER,    -- 게시글 SEQ
    PL_DATE DATE DEFAULT SYSDATE NOT NULL, -- 게시글 좋아요 누른 시간
    USER_ID VARCHAR2(50)    -- 좋아요 누른 아이디
    );

-- 매칭
CREATE TABLE MATCHING(
    MATCHING_SEQ NUMBER PRIMARY KEY, -- 매칭 SEQ
    POST_SEQ NUMBER, -- 글 SEQ
    CHATROOM_SEQ NUMBER, -- 채팅방번호
    MATCHING_APPOINTMENT DATE, -- 약속 날짜, 시간
    MATCHING_RESULT VARCHAR2(1) DEFAULT 'N' CHECK(MATCHING_RESULT IN ('Y','N')), -- 매칭성사여부
    MATCHING_DATE DATE DEFAULT SYSDATE, -- 매칭결정날짜
    UNMATCHING_DATE DATE DEFAULT SYSDATE  --언매칭날짜  
    
);


-- 매칭 참여 유저 정보
CREATE TABLE MATCHING_USER_INFO( 
    MATCHING_USER_INFO_SEQ NUMBER PRIMARY KEY, -- 매칭참여유저정보 SEQ    
    MATCHING_SEQ NUMBER,  -- 매칭 SEQ
    USER_ID VARCHAR(50) NOT NULL, -- 매칭 참여 유저 아이디
    SCORE NUMBER DEFAULT NULL CHECK(SCORE IN (1,2,3,4,5,6,7,8,9,10)), -- 받은 평점
    MATCHING_SUCCESS VARCHAR2(1) DEFAULT 'N' CHECK(MATCHING_SUCCESS IN ('Y','N')) -- 매칭성공
);

-- 채팅방
 CREATE TABLE CHATROOM (  
    CHATROOM_SEQ NUMBER PRIMARY KEY,                           -- 채팅방 SEQ
    START_DATE DATE DEFAULT SYSDATE,           -- 대화 시작 시간
    LAST_CHAT DATE,     -- 마지막 대화
    DELETE_CHATROOM VARCHAR2(1) DEFAULT 'N' CHECK(DELETE_CHATROOM IN ('Y','N'))   -- 채팅방 삭제
);

-- 채팅발송정보
CREATE TABLE CHAT_INFO (  
    CHAT_INFO_SEQ NUMBER PRIMARY KEY,
    CHATROOM_SEQ NUMBER,                                        -- 채팅방 SEQ
    USER_ID VARCHAR2(50),                                   -- 채팅발송유저아이디
    CHAT_CONTENT VARCHAR2(300) NOT NULL,                                 -- 채팅내용
    CHAT_SENT_TIME TIMESTAMP                                    -- 발송시각    
);

-- 채팅참여유저정보
CREATE TABLE CHAT_USER_INFO (
    CHAT_USER_INFO_SEQ NUMBER PRIMARY KEY,
    CHATROOM_SEQ NUMBER,                      -- 채팅방번호
    USER_ID VARCHAR2(50),               -- 채팅 참여 유저 아이디
    READ_STATUS VARCHAR2(1) DEFAULT 'N' CHECK(READ_STATUS IN('Y', 'N'))          -- 읽음표시(Y/N)
);

-- 댓글
CREATE TABLE COMMENTS( 
    COMMENTS_SEQ NUMBER PRIMARY KEY,                                                     -- 댓글 SEQ 
    POST_SEQ NUMBER,                                                   -- 게시글 SEQ
    COMMENTS_PARENT_SEQ NUMBER,                                              -- 부모댓글 SEQ
    USER_ID VARCHAR2(200),                                             -- 아이디 SEQ
    COMMENTS_DESC VARCHAR2(1000) NOT NULL,                                   -- 댓글 내용
    COMMENTS_DATE DATE DEFAULT SYSDATE NOT NULL,                             -- 댓글작성시각
    SECRET_COMMENTS VARCHAR2(1) DEFAULT 'N' CHECK(SECRET_COMMENTS IN('N','Y')),   -- 비밀댓글(Y/N)
    COMMENTS_DELETE VARCHAR2(1) DEFAULT 'N' NOT NULL CHECK(COMMENTS_DELETE IN ('Y', 'N'))  -- 댓글 삭제
);

-- 댓글 좋아요
CREATE TABLE COMMENT_LIKE(
    CL_SEQ NUMBER PRIMARY KEY,                         -- 댓글좋아요 SEQ
    CL_DATE DATE DEFAULT SYSDATE NOT NULL, -- 댓글좋아요 누른시간
    COMMENTS_SEQ NUMBER,                              -- 댓글 SEQ
    USER_ID VARCHAR2(200)                       -- 아이디
);

-- 카테고리
CREATE TABLE CATEGORY(
    CATEGORY_SEQ NUMBER PRIMARY KEY,            -- 카테고리(관심사) SEQ
    CATEGORY_NAME VARCHAR2(200) UNIQUE,    -- 카테고리(관심사) 이름
    USER_ID VARCHAR2(200),    -- 아이디
    CT_SEQ NUMBER             -- 카테고리(관심사)분류 SEQ
);

-- 카테고리(관심사)분류
CREATE TABLE CATEGORY_TYPE(
    CT_SEQ NUMBER PRIMARY KEY,           -- 카테고리(관심사)분류 SEQ
    CT_NAME VARCHAR2(200) UNIQUE  -- 카테고리(관심사)분류이름/매칭게시판 이름
);

-- 지역
CREATE TABLE PLACE(
    PLACE_SEQ NUMBER PRIMARY KEY,
    PLACE_NAME VARCHAR2(100) NOT NULL, -- 지역 이름
    PLACE_TYPE_SEQ NUMBER,      -- 지역 분류 SEQ
    USER_ID VARCHAR2(200) -- 유저아이디
);

-- 지역분류
CREATE TABLE PLACE_TYPE(
    PLACE_TYPE_SEQ NUMBER PRIMARY KEY,  
    PLACE_TYPE_NAME VARCHAR2(100) NOT NULL -- 지역 분류 이름
);

-- FK
ALTER TABLE BLOCK_USERS ADD CONSTRAINT BLOCK_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO (USER_ID);     -- 유저아이디(유저정보, 차단유저정보)
ALTER TABLE BLOCK_USERS ADD CONSTRAINT BLOCK_USER_ID_FK2 FOREIGN KEY(BLOCK_ID) REFERENCES USER_INFO (USER_ID);    -- 유저아이디(유저정보, 차단유저정보)
ALTER TABLE BAN_INFO ADD CONSTRAINT BAN_USER_ID_FK FOREIGN KEY(BAN_ID) REFERENCES USER_INFO(USER_ID);      -- 유저아이디(유저정보, 이용정지정보)
ALTER TABLE USER_LIKE ADD CONSTRAINT LIKE_USER_ID_FK FOREIGN KEY(LIKE_UP_USER) REFERENCES USER_INFO(USER_ID);   -- 유저아이디(유저정보, 유저 좋아요)
ALTER TABLE USER_LIKE ADD CONSTRAINT LIKE_USER_ID_FK2 FOREIGN KEY(LIKE_UP_TARGET) REFERENCES USER_INFO(USER_ID); -- 유저아이디(유저정보, 유저 좋아요)
ALTER TABLE POST ADD CONSTRAINT POST_USER_ID_FK FOREIGN KEY(POST_USER_ID) REFERENCES USER_INFO(USER_ID);   -- 유저아이디(유저정보, 게시글)
ALTER TABLE POST_LIKE ADD CONSTRAINT POST_LIKE_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);    -- 유저아이디(유저정보, 게시글 좋아요)
ALTER TABLE MATCHING_USER_INFO ADD CONSTRAINT MATCHING_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);      -- 유저아이디 (유저정보, 매칭) 
ALTER TABLE CHAT_USER_INFO ADD CONSTRAINT CHAT_USER_INFO_ID_FK2 FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID); -- 유저아이디(유저정보, 채팅참여유저정보)
ALTER TABLE COMMENTS ADD CONSTRAINT COMMENTS_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);            -- 유저아이디(유저정보, 댓글)
ALTER TABLE COMMENT_LIKE ADD CONSTRAINT COMMENT_LIKE_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);         -- 유저아이디(유저정보, 댓글 좋아요)
ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORY_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);         -- 유저아이디(유저정보, 카테고리(관심사))
ALTER TABLE PLACE ADD CONSTRAINT PLACE_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID);    -- 유저아이디(유저정보, 지역)

ALTER TABLE MATCHING ADD CONSTRAINT MATCHING_POST_SEQ_FK FOREIGN KEY(POST_SEQ) REFERENCES POST(POST_SEQ); -- 글 SEQ (글, 매칭)

ALTER TABLE MATCHING_USER_INFO ADD CONSTRAINT MATCHING_USER_INFO_MATCHING_SEQ_FK FOREIGN KEY(MATCHING_SEQ) REFERENCES MATCHING(MATCHING_SEQ);  -- 매칭 SEQ (매칭, 매칭참여유저정보)
ALTER TABLE POST ADD CONSTRAINT POST_MATCHING_SEQ_FK FOREIGN KEY(POST_SEQ) REFERENCES MATCHING(MATCHING_SEQ); -- 매칭 SEQ (매칭, 글)

ALTER TABLE MATCHING ADD CONSTRAINT MATCHING_CHATROOM_SEQ_FK FOREIGN KEY(CHATROOM_SEQ) REFERENCES CHATROOM(CHATROOM_SEQ); -- 채팅방 SEQ (채팅방, 매칭)
ALTER TABLE CHAT_INFO ADD CONSTRAINT CHAT_INFO_CHATROOM_SEQ_FK FOREIGN KEY(CHATROOM_SEQ) REFERENCES CHATROOM(CHATROOM_SEQ); -- 채팅방 SEQ (채팅방, 채팅정보)
ALTER TABLE CHAT_USER_INFO ADD CONSTRAINT CHAT_USER_CHATROOM_SEQ_FK FOREIGN KEY(CHATROOM_SEQ) REFERENCES CHATROOM(CHATROOM_SEQ); -- 채팅방 SEQ (채팅방, 채팅참여유저정보)

ALTER TABLE POST ADD CONSTRAINT POST_CATEGORY_SEQ_FK FOREIGN KEY(CATEGORY_SEQ) REFERENCES CATEGORY(CATEGORY_SEQ);   --  카테고리 SEQ(카테고리, 매칭글)

ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORY_CT_SEQ_FK FOREIGN KEY(CT_SEQ) REFERENCES CATEGORY_TYPE(CT_SEQ);   --  카테고리분류 SEQ(카테고리분류, 카테고리)

ALTER TABLE PLACE ADD CONSTRAINT PLACE_PT_SEQ_FK FOREIGN KEY(PLACE_TYPE_SEQ) REFERENCES PLACE_TYPE(PLACE_TYPE_SEQ); -- 지역분류 SEQ(지역분류, 지역)

ALTER TABLE POST ADD CONSTRAINT POST_PLACE_SEQ_FK FOREIGN KEY(PLACE_SEQ) REFERENCES PLACE(PLACE_SEQ); -- 지역 SEQ(지역, 게시글)

ALTER TABLE POST_LIKE ADD CONSTRAINT POST_LIKE_POST_SEQ_FK FOREIGN KEY(POST_SEQ) REFERENCES POST(POST_SEQ);          -- 게시글 SEQ(게시글, 게시글좋아요)
ALTER TABLE COMMENTS ADD CONSTRAINT COMMENTS_POST_SEQ_FK FOREIGN KEY(POST_SEQ) REFERENCES POST(POST_SEQ);   -- 게시글 SEQ(게시글, 댓글)

ALTER TABLE POST ADD CONSTRAINT POST_BOARD_SEQ_FK FOREIGN KEY(BOARD_SEQ) REFERENCES BOARD(BOARD_SEQ);  -- 게시판 SEQ(게시판, 게시글)

ALTER TABLE POST ADD CONSTRAINT POST_POST_THEMA_SEQ_FK FOREIGN KEY(POST_THEMA_SEQ) REFERENCES POST_THEMA(POST_THEMA_SEQ);  -- 말머리 SEQ(말머리, 게시글)

ALTER TABLE COMMENT_LIKE ADD CONSTRAINT COMMENT_LIKE_COMMENTS_SEQ_FK FOREIGN KEY(COMMENTS_SEQ) REFERENCES COMMENTS(COMMENTS_SEQ);  -- 댓글 SEQ(댓글, 댓글좋아요)


-- 시퀀스 생성
CREATE SEQUENCE SEQ_BAN_INFO;   --차단유저 SEQ
CREATE SEQUENCE SEQ_BLOCK_USERS;  --정지유저 SEQ
CREATE SEQUENCE SEQ_BOARD; -- 게시판 SEQUENCE
CREATE SEQUENCE SEQ_CATEGORY;             -- 카테고리 SEQ
CREATE SEQUENCE SEQ_CATEGORY_TYPE;         -- 카테고리(관심사)분류 SEQ
CREATE SEQUENCE SEQ_CHAT_INFO; -- 채팅발송정보SEQ
CREATE SEQUENCE SEQ_CHAT_USER_INFO; -- 채팅참여유저정보SEQ
CREATE SEQUENCE SEQ_CHATROOM; -- 채팅방SEQ
CREATE SEQUENCE SEQ_COMMENT_LIKE;         -- 댓글 좋아요 SEQ
CREATE SEQUENCE SEQ_COMMENTS;             -- 댓글 SEQ
CREATE SEQUENCE SEQ_MATCHING;             -- 매칭 SEQ
CREATE SEQUENCE SEQ_MATCHING_USER_INFO;             -- 매칭유저정보 SEQ
CREATE SEQUENCE SEQ_PLACE;             -- 지역 SEQ
CREATE SEQUENCE SEQ_PLACE_TYPE;             -- 지역분류 SEQ
CREATE SEQUENCE SEQ_POST; -- 게시글 SEQUENCE
CREATE SEQUENCE SEQ_POST_THEMA; -- 말머리 SEQ
CREATE SEQUENCE SEQ_POST_LIKE; -- 게시글 좋아요 SEQ
CREATE SEQUENCE SEQ_USER_LIKE; -- 유저 좋아요 SEQ

-- 시퀀스 삭제
DROP SEQUENCE SEQ_BAN_INFO;   --차단유저 SEQ
DROP SEQUENCE SEQ_BLOCK_USERS;  --정지유저 SEQ
DROP SEQUENCE SEQ_BOARD; -- 게시판 SEQUENCE
DROP SEQUENCE SEQ_CATEGORY;             -- 카테고리 SEQ
DROP SEQUENCE SEQ_CATEGORY_TYPE;         -- 카테고리(관심사)분류 SEQ
DROP SEQUENCE SEQ_CHAT_INFO; -- 채팅발송정보SEQ
DROP SEQUENCE SEQ_CHAT_USER_INFO; -- 채팅참여유저정보SEQ
DROP SEQUENCE SEQ_CHATROOM; -- 채팅방SEQ
DROP SEQUENCE SEQ_COMMENT_LIKE;         -- 댓글 좋아요 SEQ
DROP SEQUENCE SEQ_COMMENTS;             -- 댓글 SEQ
DROP SEQUENCE SEQ_MATCHING;             -- 매칭 SEQ
DROP SEQUENCE SEQ_MATCHING_USER_INFO;             -- 매칭유저정보 SEQ
DROP SEQUENCE SEQ_PLACE;             -- 지역 SEQ
DROP SEQUENCE SEQ_PLACE_TYPE;             -- 지역분류 SEQ
DROP SEQUENCE SEQ_POST; -- 게시글 SEQUENCE
DROP SEQUENCE SEQ_POST_THEMA; -- 말머리 SEQ
DROP SEQUENCE SEQ_POST_LIKE; -- 게시글 좋아요 SEQ
DROP SEQUENCE SEQ_USER_LIKE; -- 유저 좋아요 SEQ