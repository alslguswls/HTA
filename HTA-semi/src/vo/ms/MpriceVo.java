package vo.ms;

public class MpriceVo {
	private int mp_no;
	private int bnum;
	private String id;
	private int maxprice;
	public MpriceVo() {
		super();
	}
	public MpriceVo(int mp_no, int bnum, String id, int maxprice) {
		super();
		this.mp_no = mp_no;
		this.bnum = bnum;
		this.id = id;
		this.maxprice = maxprice;
	}
	public int getMp_no() {return mp_no;}
	public void setMp_no(int mp_no) {this.mp_no = mp_no;}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public int getMaxprice() {return maxprice;}
	public void setMaxprice(int maxprice) {this.maxprice = maxprice;}
}
