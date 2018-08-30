package board.controll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDao;
import board.vo.boardVo;
import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.ms.BoardDao;
import lib.lib;
import vo.ms.BoardVo;

@WebServlet("/boardList.do")
public class boardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		String mod = request.getParameter("mod");
		
		if(!mod.equals("") && mod.equals("list")) {
			list(request,response);
		}else if(!mod.equals("") && mod.equals("getInfo")) {//글정보 가져오기
			getInfo(request,response);
		}else if(!mod.equals("") && mod.equals("delete")) {
			delete(request,response);
		}else if(!mod.equals("") && mod.equals("getCate")) {
			ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
			list1=leftList();
			request.setAttribute("list1", list1);
			request.getRequestDispatcher("/board/newBoard.jsp");
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		boardDao dao = boardDao.getInstance();
		int n=dao.delete(bnum);
		if(n>0) {
			response.sendRedirect("boardList.do?mod=list");
		}else {
			request.setAttribute("errMsg", "오류로 인해 저장에 실패 했습니다.");
			request.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(request, response);
		}
	}



	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String category_no=request.getParameter("cate");
		String order=request.getParameter("cul");
		String where = "";
		String search = request.getParameter("search");
		
		//정렬 체크
		int cul = 0;
		if(order!=null) {
			cul = Integer.parseInt(order);
		}
		order = lib.orderBy(cul);
		if(cul==3) {
			//경매 마감을 제외한 경매임박순
			where = "and status=0";
		}
		
		//카테고리 체크
		int cate=0;
		if(category_no!=null) {
			cate=Integer.parseInt(category_no);
		}
		
		//조회 검색 체크
		if(search!=null) {
			String sel = request.getParameter("searchSel");
			if(sel.equals("0")) {
				where += " and title like '%"+search+"%'";
			}else {
				where +=" and id like '%"+search+"%'";
			}
		}
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
		list1=leftList();
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		boardDao dao=boardDao.getInstance();
		ArrayList<boardVo> list=dao.list(startRow, endRow, cate, order, where);
		//전체페이지갯수구하기
		int pageCount=(int)Math.ceil(dao.getCount(cate)/10.0);
		//시작페이지번호
		int startPage=((pageNum-1)/10*10)+1;
		//끝페이지번호
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("list",list);
		request.setAttribute("list1",list1);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("cate",cate);
		request.setAttribute("search",search);
		request.getRequestDispatcher("/layout.jsp?page=/board/list.jsp").forward(request, response);
	}
	
	private void getInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao dao=new BoardDao();
		BoardVo vo = dao.detail(bnum);
		request.setAttribute("vo",vo);
		request.getRequestDispatcher("/layout.jsp?page=/board/newBoard.jsp").forward(request, response);
	}
	
	//left category list
		private ArrayList<CategoryVo> leftList(){
			CategoryDao dao= CategoryDao.getInstance();
			ArrayList<CategoryVo> list = new ArrayList<CategoryVo>();
			list=dao.leftList();
			return list;	
		}
}
