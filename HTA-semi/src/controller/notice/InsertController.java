package controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.notice.AdminDAO;
import vo.notice.NoticeVO;



@WebServlet("/notiinsert.do")
public class InsertController extends HttpServlet{
	@Override//공지사항 등록 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int insert=0;
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		System.out.println(title);
		System.out.println(content);
		if (title.equals("") || content.equals("")) {
			
			request.setAttribute("errMsg", "빈칸 존재로 인한 등록 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=notiError.jsp");
			rd.forward(request, response);
			return;
		} else {
			NoticeVO vo = new NoticeVO(0 , title, content, null);
			AdminDAO dao =  AdminDAO.getInstance();
			 insert = dao.insert(vo);
			
		}
		
		
		if (insert>0) {
			response.sendRedirect("noticeList.do");
		}  else {
			request.setAttribute("errMsg", "등록과정의 오류로인한 등록 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=notiError.jsp");
			rd.forward(request, response);
		}
	}
}
