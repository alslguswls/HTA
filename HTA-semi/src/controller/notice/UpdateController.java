package controller.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.notice.AdminDAO;
import vo.notice.NoticeVO;



@WebServlet("/notiupdate.do")
public class UpdateController extends HttpServlet {
	//수정을 위해 특정 공지사항을 호출하는 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		
		NoticeVO vo2 = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		vo2 = dao.invite(num);
		
	//	CategoryDao cdao = CategoryDao.getInstance();
	//	ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
	//	list1=cdao.leftList();
	//	request.setAttribute("list1", list1);
		request.setAttribute("vo2",vo2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=noticeUpdate.jsp&left=noticeLeft.jsp");
		rd.forward(request, response);
		
	}

}