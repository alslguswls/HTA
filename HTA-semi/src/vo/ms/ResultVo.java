package vo.ms;

import java.util.Date;

public class ResultVo {
	private int rnum;
	private int bnum;
	private String id;
	private int price;
	private Date endtime;
	public ResultVo() {
	}
	public ResultVo(int rnum, int bnum, String id, int price, Date endtime) {
		this.rnum = rnum;
		this.bnum = bnum;
		this.id = id;
		this.price = price;
		this.endtime = endtime;
	}
	public int getRnum() {return rnum;}
	public void setRnum(int rnum) {this.rnum = rnum;}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public Date getEndtime() {return endtime;}
	public void setEndtime(Date endtime) {this.endtime = endtime;}
}
