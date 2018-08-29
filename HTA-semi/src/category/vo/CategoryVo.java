package category.vo;

public class CategoryVo {
	private int cate;
	private String name;
	
	public CategoryVo() {}
	
	public CategoryVo(int cate, String name) {
		this.cate=cate;
		this.name=name;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
