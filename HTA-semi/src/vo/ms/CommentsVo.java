package vo.ms;

public class CommentsVo {
	private int cnum;
	private int bnum;
	private String id;
	private String comments;
	private int ref;
	private int lev;
	private int step;
	
	public CommentsVo() {
	}
	public CommentsVo(int cnum, int bnum, String id, String comments, int ref, int lev, int step) {
		this.cnum = cnum;
		this.bnum = bnum;
		this.id = id;
		this.comments = comments;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}
	public int getCnum() {return cnum;}
	public void setCnum(int cnum) {this.cnum = cnum;}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getComments() {return comments;}
	public void setComments(String comments) {this.comments = comments;}
	public int getRef() {return ref;}
	public void setRef(int ref) {this.ref = ref;}
	public int getLev() {return lev;}
	public void setLev(int lev) {this.lev = lev;}
	public int getStep() {return step;}
	public void setStep(int step) {this.step = step;}
}
