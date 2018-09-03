######  유저 테이블    #######
drop table users;
create table users(
	id varchar2(100) primary key, --아이디
	pwd varchar2(100), --비밀번호
	email varchar2(100), --이메일
	phone varchar2(20), --전화번호
	addr varchar2(100), --주소
	lev number(10), --레벨
	coin number(38), --코인
	regdate date,
	pwd_enc varchar2(100)
)tablespace semi;

######  게시판 테이블    #######
drop table board;
create table board( 
	bnum number(38), --게시글 번호
	id varchar2(100), --아이디
	cate number(10) not null, --카테고리
	title varchar2(100), --제목
	content varchar2(100), --내용
	orgfilename varchar2(100), --오리지널파일네임
	savefilename varchar2(100), --전장파일네임
	starttime date, --경매시작시간
	startprice number(38)default 0 NOT NULL, --시작경매가
	hit number(10)default 0 NOT NULL, --조회수
	regv number(10)default 0 NOT NULL, -- 예약자수
	status number(1) default 0 NOT NULL, --상태값
	regdate date default sysdate, --글등록일 수정일
	CONSTRAINT pk_board PRIMARY KEY (bnum),
	CONSTRAINT fk_board FOREIGN KEY(id) references users(id)
)tablespace semi;
drop sequence board_seq;
create sequence board_seq;


######  카테고리 테이블    #######
drop table category;
create table category(
	cate number(10) primary key, --카테고리 번호
	name varchar2(100) unique --카테고리 이름
)tablespace semi;
create sequence cate_seq;


######  예약 테이블    #######
drop table reservation;
create table reservation(
	vnum number(10) primary key, --예약자 번호
	bnum number(38), --게시글번호
	id varchar2(100), --작성자 아이디
	CONSTRAINT fk_reservation_users FOREIGN KEY(bnum) references board(bnum) ON DELETE CASCADE,
	CONSTRAINT fk_reservation_board FOREIGN KEY(id) references users(id) ON DELETE CASCADE
)tablespace semi;
drop sequence reservation_seq;
create sequence reservation_seq;

######  댓글 테이블    #######
drop table comments;
create table comments(
	cnum number(38) primary key, --댓글번호
	bnum number(38), --게시글 번호
	id varchar2(100), --아이디
	comments varchar2(2000), --댓글
	ref number(10), --글번호
	lev number(10), --댓긇번호
	step number(10), --대댓글
	CONSTRAINT fk_comments_users FOREIGN KEY(bnum) references board(bnum) ON DELETE CASCADE,
	CONSTRAINT fk_comments_board FOREIGN KEY(id) references users(id) ON DELETE CASCADE
)tablespace semi;
drop sequence comment_seq;
create sequence comment_seq;

######  입찰내역 테이블    #######
drop table result;
create table result(
	rnum number(38) primary key, --판매번호 
	bnum number(38), -- 글번호
	id varchar2(100), --구매자
	price number(38), --구매가격 
	endtime Date, --종료시간/구매일시
	CONSTRAINT fk_comment_users FOREIGN KEY(bnum) references board(bnum) ON DELETE CASCADE,
	CONSTRAINT fk_comment_board FOREIGN KEY(id) references users(id) ON DELETE CASCADE
)tablespace semi;
drop sequence result_seq;
create sequence result_seq;


######  채팅 테이블    #######
drop table chat;
create table chat(
	chat_no number(38) primary key, --채팅번호
	bnum number(38), --글번호
	id varchar2(100), --아이디
	str varchar2(2000), --채팅내용
	status number(1) default 0 NOT NULL, --상태값
	CONSTRAINT fk_chat_users FOREIGN KEY(bnum) references board(bnum) ON DELETE CASCADE,
	CONSTRAINT fk_chat_board FOREIGN KEY(id) references users(id) ON DELETE CASCADE
)tablespace semi;
drop sequence chat_seq;
create sequence chat_seq;


######입찰진행 호가    #######
drop table mprice;
create table mprice(
	mp_no number(38) primary key, --입찰번호
	bnum number(38), --글번호
	id varchar2(100), --아이디
	maxprice number(38), --최고가
	CONSTRAINT fk_max_users FOREIGN KEY(bnum) references board(bnum) ON DELETE CASCADE,
	CONSTRAINT fk_max_board FOREIGN KEY(id) references users(id) ON DELETE CASCADE
)tablespace semi;
drop sequence max_seq;
create sequence max_seq;


######  공지사항 테이블    #######
drop table noti;
create table noti(
	noti_no number(38) primary key, --공지번호
	title varchar2(100), --제목
	content varchar2(2000), --내용
	regdate date default sysdate --등록일
);
drop sequence noti_seq;
create sequence noti_seq;





