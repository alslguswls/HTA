package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.AdminDAO;
import test.vo.NoticeVO;
@WebServlet("/insert.do")
public class InsertController extends HttpServlet{
	@Override//글 등록위한 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("tltle");
		String content = request.getParameter("content");
		NoticeVO vo = new NoticeVO(0 , title, content, null);
		AdminDAO dao =  AdminDAO.getInstance();
		int insert = dao.insert(vo);
		if (insert>0) {
			response.sendRedirect("notice.jsp");
		} else {
			request.setAttribute("errMsg", "오류로 인한 수정실패");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
