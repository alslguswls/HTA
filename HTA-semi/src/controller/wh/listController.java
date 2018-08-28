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
2018. 8. 24	회원 목록 조회 기능 작성		윤우현 
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
			// 끝 페이지 번호★★
			int endPage = startPage+9;
			if(endPage>pageCount) {
				endPage=pageCount;
			}
			// 결과값(전체회원정보)를 스코프에 담기
			request.setAttribute("list",list);
			request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			// 페이지로 이동하기
			request.getRequestDispatcher("/member.jsp").forward(request, response);
	}

}
