package vo.ms;

public class ReservationVo {
	private int vnum;
	private int bnum;
	private String id;
	
	public ReservationVo() {
	}
	public ReservationVo(int vnum, int bnum, String id) {
		this.vnum = vnum;
		this.bnum = bnum;
		this.id = id;
	}
	
	public int getVnum() {return vnum;}
	public void setVnum(int vnum) {this.vnum = vnum;}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
}
