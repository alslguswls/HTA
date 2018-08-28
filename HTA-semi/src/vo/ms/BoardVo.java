package vo.ms;

import java.sql.Date;

public class BoardVo {
	private int bnum;
	private String id;
	private int cate;
	private String title;
	private String content;
	private String orgfilename;
	private String savefilename;
	private String starttime;
	private int startprice;
	private int hit;
	private int regv;
	private int status;
	private Date regdate;
	public BoardVo() {
	}
	public BoardVo(int bnum, String id, int cate, String title, String content, String orgfilename, String savefilename,
			String starttime, int startprice, int hit, int regv, int status, Date regdate) {
		this.bnum = bnum;
		this.id = id;
		this.cate = cate;
		this.title = title;
		this.content = content;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.starttime = starttime;
		this.startprice = startprice;
		this.hit = hit;
		this.regv = regv;
		this.status = status;
		this.regdate = regdate;
	}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public int getCate() {return cate;}
	public void setCate(int cate) {this.cate = cate;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public String getOrgfilename() {return orgfilename;}
	public void setOrgfilename(String orgfilename) {this.orgfilename = orgfilename;}
	public String getSavefilename() {return savefilename;}
	public void setSavefilename(String savefilename) {this.savefilename = savefilename;}
	public int getStartprice() {return startprice;}
	public void setStartprice(int startprice) {this.startprice = startprice;}
	public int getHit() {return hit;}
	public void setHit(int hit) {this.hit = hit;}
	public int getRegv() {return regv;}
	public void setRegv(int regv) {this.regv = regv;}
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
	public Date getRegdate() {return regdate;}
	public void setRegdate(Date regdate) {this.regdate = regdate;}
	public String getStarttime() {return starttime;}
	public void setStarttime(String starttime) {this.starttime = starttime;}
}
