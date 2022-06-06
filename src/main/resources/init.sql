--CREATE database IF NOT EXISTS DW DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
--	DB에 DW가 없으면 생성해라!
--USE DW; 
--	데이터베이스를 만들면 사용하겠다는 의미
--	여기서 root 같은 username를 정의할 수도 있다.


-- 학생 테이블
CREATE TABLE IF NOT EXISTS students(
--	students 테이블이 존재하지 않는다면 생성해라!
	students_id	INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '학생 아이디',
--	AUTO INCREMENT : 자동으로 1씩 증가(PK가 중복되지 않도록 자동으로 증가함)
    students_name VARCHAR(20) COMMENT '학생 이름',
    students_password VARCHAR(200) COMMENT '학생 비밀번호',
--    비밀번호의 글자수가 큰 이유 : 비밀번호를 개발자도 모르게 암호화하기 때문에
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입 날짜'
--    해당 값을 입력하지 않으면 DEFUALT 값 현재날짜(CURRENT_TIMESTAMP)를 넣겠다
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 게시판 테이블
CREATE TABLE IF NOT EXISTS board(
	board_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 아이디',
    students_id INTEGER(4) COMMENT '학생 아이디',
    title VARCHAR(50) COMMENT '제목',
    content VARCHAR(100) COMMENT '글 내용',
    update_at DATETIME COMMENT '수정 날짜',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성 날짜',
    cnt integer(4) DEFAULT 0 COMMENT '조회수',
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ENGINE과 CHARSET은 입력하지 않아도 자동으로 설정되지만, 엔진을 변경하거나 인코딩 포맷을 변경할 때 사용한다.

-- 접속이력 테이블
CREATE TABLE IF NOT EXISTS board_logs
(
	log_id BIGINT(20) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '로그 아이디',
	ip VARCHAR(50) COMMENT '아이피',
	latitude VARCHAR(20) COMMENT '위도',
	longitude VARCHAR(20) COMMENT '경도',
	url VARCHAR(100) COMMENT '요청 url',
	http_method VARCHAR(10) COMMENT 'http method',
	create_at DATETIME COMMENT '접속 시간'
--	create_at은 default값이 없음
--	이유) 실제로 접속한 시간과, 자바 처리하는 시간때문에 delay된 insert 시간이 다르기 때문.
--	서버에서 시간을 구한다음에 insert할 것임
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 이제 프로젝트를 실행하면 DB에 테이블이 생성된다.