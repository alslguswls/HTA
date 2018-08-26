package vo.ms;

public class ChatVo {
	private int chat_no;
	private int bnum;
	private String id;
	private String str;
	private int status;
	public ChatVo() {
	}
	public ChatVo(int chat_no, int bnum, String id, String str, int status) {
		this.chat_no = chat_no;
		this.bnum = bnum;
		this.id = id;
		this.str = str;
		this.status = status;
	}
	public int getChat_no() {return chat_no;}
	public void setChat_no(int chat_no) {this.chat_no = chat_no;}
	public int getBnum() {return bnum;}
	public void setBnum(int bnum) {this.bnum = bnum;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getStr() {return str;}
	public void setStr(String str) {this.str = str;}
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
}
