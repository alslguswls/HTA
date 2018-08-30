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
	
	public static String[] bStatus() {
		String status[] =  {
			"경매대기중",
			"경매진행중",
			"경매종료",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"삭제됨"
		};
		return status;
	}
	
	
	
}
