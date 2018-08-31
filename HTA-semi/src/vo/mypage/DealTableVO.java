package vo.mypage;

import java.util.Date;

public class DealTableVO {
	private int rnum;
	private int bnum;
	private String dealid;
	private int price;
	private Date endtime;
	private String title;
	
	public DealTableVO() {
		
	}

	public DealTableVO(int rnum, int bnum, String dealid, int price, Date endtime, String title) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.dealid = dealid;
		this.price = price;
		this.endtime = endtime;
		this.title = title;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getDealid() {
		return dealid;
	}

	public void setDealid(String dealid) {
		this.dealid = dealid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "DealTableVO [rnum=" + rnum + ", bnum=" + bnum + ", dealid=" + dealid + ", price=" + price + ", endtime="
				+ endtime + ", title=" + title + "]";
	}
	
	
}
