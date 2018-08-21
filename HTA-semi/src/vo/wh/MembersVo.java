package vo.wh;

public class MembersVo {
	private String id;			// ����� ���̵�
	private String pwd;		// ����� �н�����
	private String email;	// ����� �̸���
	private String phone;	// ����� ��ȭ��ȣ
	private String addr;		// ����� �ּ�
	private int lev;				// ����� ���
	private Double coin;	//	����
	
	public MembersVo() {} // ����Ʈ������

	// ������
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
	
	// getter �޼ҵ�
		@Override
		public String toString() {
			return "MembersVo [id=" + id + ", pwd=" + pwd + ", email=" + email + ",phone=" + phone + ", addr=" + addr + ", lev="
					+ lev + ",coin=" + coin + "]";
		}
}	
