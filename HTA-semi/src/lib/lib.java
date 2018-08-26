package lib;

public class lib {
	
	public String[] category(){
		//카테고리 
		String arr[] = {"컴퓨터","지갑","핸드폰"};
		return arr;
	}
	public static String orderBy(int cul) {
		String oder[] = {
				"regdate desc",//기본 값 최근등록일
				"hit desc", //조회순
				"starttime asc"//마감 입박순
		};
		return oder[cul];
	}
	
	
	
}
