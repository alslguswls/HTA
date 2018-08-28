package vo.notice;

import java.util.Date;

public class NoticeVO {
	private int noti_no;
	private String title;
	private String content;
	private Date regdate;
	
	public NoticeVO() {
		
	}

	public NoticeVO(int noti_no, String title, String content, Date regdate) {
		super();
		this.noti_no = noti_no;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getNoti_no() {
		return noti_no;
	}

	public void setNoti_no(int noti_no) {
		this.noti_no = noti_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "NoticeVO [noti_no=" + noti_no + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}
	
	
	
	
}
