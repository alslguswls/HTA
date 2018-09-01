package vo.mypage;

public class ReservationTitleVO {
	private int bnum;
	private String title;
	
	public ReservationTitleVO() {
		
	}

	public ReservationTitleVO(int bnum, String title) {
		super();
		this.bnum = bnum;
		this.title = title;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ReservationTitleVO [bnum=" + bnum + ", title=" + title + "]";
	}
	
	
}
