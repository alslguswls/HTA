package vo.notice;

public class SearchVO {
	private String search;
	private String keyword;
	
	public SearchVO() {
		
	}

	public SearchVO(String search, String keyword) {
		super();
		this.search = search;
		this.keyword = keyword;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchVO [search=" + search + ", keyword=" + keyword + "]";
	}
	
	
}
