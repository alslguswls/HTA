package controller.wh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import vo.wh.MembersVo;

/**
 * Servlet implementation class listController
 */
@WebServlet("/memberList.do")
public class listController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String spageNum = request.getParameter("pageNum");
			int pageNum=1;
			if(spageNum != null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10 +1;
			int endRow=startRow+9;
			MembersDao dao=new MembersDao();
			ArrayList<MembersVo> list=dao.list(startRow, endRow);
			// 전체 페이지 수 구하기. Math.ceil --> 올림
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			// 시작 페이지 번호
			int startPage = ((pageNum-1)/10*10)+1;
			// 끝 페이지 번호
			int endPage = startPage+1;
			if(endPage>pageCount) {
				endPage=pageCount;
			}
			request.setAttribute("list",list);
			request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.getRequestDispatcher("/member.jsp").forward(request, response);
	}

}