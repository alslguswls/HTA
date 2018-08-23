package board.vo;

import java.sql.Date;
//게시판
public class boardVo {
	private int bnum; //게시글 넘버 
	private String id; //작성자 아이
	private int cate; //카테고리 
	private String title; //제목
	private String content; //내용
	private String orgfilename; //업로드 파일 이름
	private String savefilename;//서버저장 파일 이름
	private String starttime;//경매시작시간
	private int startprice;//경매시작가
	private int hit;//조회수
	private int regv;//경매 예약자 수
	private int status;//상태값
	private Date regdate; //등록시간
	
	public boardVo() {}
	
	//전체 데이터 변수 사용
	public boardVo(int bnum,String id,int cate, String title, String content, String orgfilename,String savefilename,String starttime,int startprice,int hit,int regv,int status,Date regdate) {
		this.bnum=bnum;
		this.id=id;
		this.cate=cate;
		this.title=title;
		this.content=content;
		this.orgfilename=orgfilename;
		this.savefilename=savefilename;
		this.starttime=starttime;
		this.startprice=startprice;
		this.hit=hit;
		this.regv=regv;
		this.status=status;
		this.regdate=regdate;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public int getStartprice() {
		return startprice;
	}

	public void setStartprice(int startprice) {
		this.startprice = startprice;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getRegv() {
		return regv;
	}

	public void setRegv(int regv) {
		this.regv = regv;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
