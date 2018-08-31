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

/**
 * Servlet implementation class WriteAddressController
 */
@WebServlet("/writeaddr.do")
public class WriteAddressController extends HttpServlet {
		//write.jsp left쪽 카테고리 자료 전송을 위한 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	CategoryDao cdao = CategoryDao.getInstance();
	//	ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
	//	list1=cdao.leftList();
		//System.out.println(list1);
	//	request.setAttribute("list1", list1);
		
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=write.jsp&left=noticeLeft.jsp");
		rd.forward(request, response);
	}

}
