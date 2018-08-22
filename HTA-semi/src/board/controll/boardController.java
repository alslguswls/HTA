package board.controll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDao;
import board.vo.boardVo;

@WebServlet("/boardList.do")
public class boardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cate=Integer.parseInt(request.getParameter("cate"));
		
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		boardDao dao=boardDao.getInstance();
		ArrayList<boardVo> list=dao.list(startRow, endRow, cate);
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
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageNum",pageNum);
		request.getRequestDispatcher("/layout.jsp?page=/board/list.jsp").forward(request, response);
	}
}
