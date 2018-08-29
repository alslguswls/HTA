package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

@WebServlet("/detail.do")
public class DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("detail")) {
			detail(request, response);
		}else if(cmd != null && cmd.equals("check")) {
			check(request, response);
		}
	}
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.detail(bnum);
		request.setAttribute("status", vo.getStatus());
		if(vo != null) {
			dao.hitup(bnum);
			String id = (String)request.getSession().getAttribute("id");
			ReservationDao rdao = new ReservationDao();
			if(rdao.select(new ReservationVo(0, bnum, id))) {
				request.setAttribute("resv", true);
			}else {
				request.setAttribute("resv", false);
			}
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/layout.jsp?page=detail.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "오류로 인해 조회에 실패했습니다.");
			request.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(request, response);
		}
	}
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.detail(bnum);
		int status = vo.getStatus();
//		String time = vo.getStarttime();
//		String[] day = time.substring(0,10).split("-");
//		int year = Integer.parseInt(day[0]);
//		int month = Integer.parseInt(day[1]);
//		int date = Integer.parseInt(day[2]);
//		int hourOfDay = Integer.parseInt(time.substring(11,13));
//		int minute = Integer.parseInt(time.substring(14,16));
//		Calendar cal = Calendar.getInstance();
//		cal.set(year, month-1, date, hourOfDay, minute);
//		Calendar sysdate = Calendar.getInstance();
		
		JSONObject json = new JSONObject();
		if(status == 1) {
			json.put("open", true);
		}else {
			json.put("open", false);
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
}
