package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.ms.BoardDao;
import dao.ms.ReservationDao;
import vo.ms.BoardVo;
import vo.ms.ReservationVo;

@WebServlet("/resv.do")
public class ReservationController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("resv")) {
			resv(request, response);
		}
	}
	protected void resv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = request.getParameter("id");
		
		ReservationDao dao = new ReservationDao();
		BoardDao bdao = new BoardDao();
		BoardVo vo = bdao.detail(bnum);
		JSONObject json = new JSONObject();
		if(vo.getStatus() == 0) {
			int n = dao.insert(new ReservationVo(0, bnum, id));
			if(n > 0) {
				new BoardDao().resvup(bnum);
				json.put("result", true);
			}else{
				json.put("result", false);
			}
		}else {
			json.put("result", false);
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
}
