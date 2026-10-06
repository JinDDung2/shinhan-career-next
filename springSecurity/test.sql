DROP TABLE IF EXISTS tb_mem;

CREATE TABLE tb_mem (
                         mem_id       VARCHAR(50)  PRIMARY KEY,
                         mem_nm       VARCHAR(50)  NOT NULL,
                         mem_cd       CHAR(1)      DEFAULT '0',
                         pwd          VARCHAR(100) NOT NULL,
                         profile_img  VARCHAR(255),

                         CONSTRAINT chk_mem_cd CHECK (mem_cd IN ('0', '1'))
);

-- DelegatingPasswordEncoder는 {bcrypt}, {noop} 같은 접두어로
-- 비밀번호 비교 방식을 결정합니다.
-- 실습용 초기 데이터는 {noop}을 사용합니다.
-- 회원가입으로 생성한 계정은 {bcrypt} 형식으로 저장할 수 있습니다.

INSERT INTO tb_mem (mem_id, mem_nm, mem_cd, pwd, profile_img)
VALUES ('kim', '김미연', '0', '{noop}1234', 'a.png');

INSERT INTO tb_mem (mem_id, mem_nm, mem_cd, pwd, profile_img)
VALUES ('lee', '이철수', '1', '{noop}1234', 'b.png');