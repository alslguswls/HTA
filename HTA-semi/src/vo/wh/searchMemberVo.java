package vo.wh;

/*
2018-08-30	회원게시판 검색기능 vo 생성(윤우현)
 */

public class searchMemberVo {
	private String searchSel;
	private String searchText;
	
	public searchMemberVo() {
		
	}
	
	public searchMemberVo(String searchSel, String searchText) {
		super();
		this.searchSel = searchSel;
		this.searchText = searchText;
	}



	public String getSearchSel() {
		return searchSel;
	}

	public void setSearchSel(String searchSel) {
		this.searchSel = searchSel;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public String toString() {
		return "searchMemberVo [searchSel=" + searchSel + ", searchText=" + searchText + "]";
	}
	
}
