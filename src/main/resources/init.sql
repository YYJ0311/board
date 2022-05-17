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
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 이제 프로젝트를 실행하면 DB에 테이블이 생성된다.