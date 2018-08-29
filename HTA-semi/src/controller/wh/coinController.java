package controller.wh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import javafx.scene.control.Alert;
import vo.wh.MembersVo;

/**

 */
@WebServlet("/getCoin.do")
public class coinController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id");
		MembersDao dao = new MembersDao();
		MembersVo vo1 = dao.getinfo(id);
		
		if(vo1 != null) {
			request.setAttribute("vo1", vo1);
			RequestDispatcher rd = request.getRequestDispatcher("layout.jsp?page=addcoin.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("errMsg", "오류로 인해 코인 충전을 실패 하였습니다");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
