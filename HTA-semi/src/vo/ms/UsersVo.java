package vo.ms;

public class UsersVo {
	private String id;
	private String pwd;
	private String email;
	private String phone;
	private String addr;
	private int lev;
	private int coin;
	public UsersVo() {
	}
	public UsersVo(String id, String pwd, String email, String phone, String addr, int lev, int coin) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.lev = lev;
		this.coin = coin;
	}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}
	public String getAddr() {return addr;}
	public void setAddr(String addr) {this.addr = addr;}
	public int getLev() {return lev;}
	public void setLev(int lev) {this.lev = lev;}
	public int getCoin() {return coin;}
	public void setCoin(int coin) {this.coin = coin;}
}
