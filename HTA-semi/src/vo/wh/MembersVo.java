package vo.wh;

public class MembersVo {
	private String id;			// 사용자 아이디
	private String pwd;		// 사용자 패스워드
	private String email;	// 사용자 이메일
	private String phone;	// 사용자 전화번호
	private String addr;		// 사용자 주소
	private int lev;				// 사용자 등급
	private Double coin;	//	코인
	
	public MembersVo() {} // 디폴트생성자

	// 생성자
	public MembersVo(String id, String pwd, String email, String phone, String addr, int lev, Double coin) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.lev = lev;
		this.coin = coin;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public Double getCoin() {
		return coin;
	}

	public void setCoin(Double coin) {
		this.coin = coin;
	}
	
	// getter 메소드
		@Override
		public String toString() {
			return "MembersVo [id=" + id + ", pwd=" + pwd + ", email=" + email + ",phone=" + phone + ", addr=" + addr + ", lev="
					+ lev + ",coin=" + coin + "]";
		}
}	
