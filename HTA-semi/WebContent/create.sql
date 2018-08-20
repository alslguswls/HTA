######  유저 테이블    #######
create table users(
	id varchar2(100) primary key,
	pwd varchar2(100),
	email varchar2(100),
	phone varchar2(20),
	addr varchar2(100),
	level number(10),
	coin number(38)
)tablespace semi;

######  게시판 테이블    #######
create table board( 
	bnum number(38),
	id varchar2(100),
	cate number(10) not null,
	title varchar2(100),
	content varchar2(100),
	orgfilename varchar2(100),
	savefilename varchar2(100),
	starttime date default sysdate,
	startprice number(38)default 0 NOT NULL,
	hit number(10)default 0 NOT NULL,
	regv number(10)default 0 NOT NULL,
	status number(1) default 0 NOT NULL,
	CONSTRAINT pk_board PRIMARY KEY (bnum),
	CONSTRAINT fk_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence board_seq;


######  카테고리 테이블    #######
create table category(
	cate number(10) primary key,
	name varchar2(100) unique
)tablespace semi;
create sequence cate_seq;


######  예약 테이블    #######
create table reservation(
	vnum number(10) primary key,
	bnum number(38),
	id varchar2(100),
	CONSTRAINT fk_reservation_users FOREIGN KEY(bnum) references board(bnum),
	CONSTRAINT fk_reservation_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence reservation_seq;

######  댓글 테이블    #######
create table comments(
	cnum number(38) primary key,
	bnum number(38),
	id varchar2(100),
	comments varchar2(2000),
	ref number(10),
	lev number(10),
	step number(10),
	CONSTRAINT fk_comments_users FOREIGN KEY(bnum) references board(bnum),
	CONSTRAINT fk_comments_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence comment_seq;

######  입찰내역 테이블    #######
create table result(
	rnum number(38) primary key,
	bnum number(38),
	id varchar2(100),
	price number(38),
	endtime number(38),
	CONSTRAINT fk_comment_users FOREIGN KEY(bnum) references board(bnum),
	CONSTRAINT fk_comment_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence result_seq;


######  채팅 테이블    #######
create table chat(
	chat_no number(38) primary key,
	bnum number(38),
	id varchar2(100),
	str varchar2(2000),
	CONSTRAINT fk_chat_users FOREIGN KEY(bnum) references board(bnum),
	CONSTRAINT fk_chat_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence chat_seq;


######입찰진행 호가    #######
create table mprice(
	mp_no number(38) primary key,
	bnum number(38),
	id varchar2(100),
	maxprice number(38),
	CONSTRAINT fk_max_users FOREIGN KEY(bnum) references board(bnum),
	CONSTRAINT fk_max_board FOREIGN KEY(id) references users(id)
)tablespace semi;
create sequence max_seq;





######  공지사항 테이블    #######
create table noti(
	noti_no number(38) primary key,
	title varchar2(100),
	content varchar2(2000),
	regdate date default sysdate
);
create sequence noti_seq;





