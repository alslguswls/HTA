package controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.mypage.ReserDAO;

/**
 * Servlet implementation class CallBnumController
 */
@WebServlet("/callbnum.do")
public class CallBnumController extends HttpServlet {
			//아무것도 아님 예비용 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bum = Integer.parseInt(request.getParameter("bnum"));
		ReserDAO dao = new ReserDAO();
		int realbnum = dao.callBnum(bum);
		
		CategoryDao cdao = CategoryDao.getInstance();
		ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
		list1=cdao.leftList();
		
	
		request.setAttribute("list1", list1);
		
		
		
	}

}
