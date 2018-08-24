package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.ms.MpriceDao;
import dao.ms.ReservationDao;
import vo.ms.MpriceVo;
import vo.ms.ReservationVo;

@WebServlet("/enter.do")
public class EnterController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("resv")) {
			resv(request,response);
		}else if(cmd != null && cmd.equals("timer")) {
			timer(request, response);
		}else if(cmd != null && cmd.equals("call")) {
			call(request, response);
		}
	}
	protected void resv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = request.getParameter("id");
		
		ReservationDao dao = new ReservationDao();
		boolean resv = dao.select(new ReservationVo(0, bnum, id));
		JSONObject json = new JSONObject();
		if(resv) {
			json.put("resv", true);
		}else {
			json.put("resv", false);
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void timer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(d);
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		MpriceDao dao = new MpriceDao();
		MpriceVo vo = dao.select(bnum);
		
		JSONObject json = new JSONObject();
		json.put("time", time);
		if(vo != null) {
			json.put("id", vo.getId());
			json.put("maxPrice", vo.getMaxprice());
		}else {
			json.put("id", "-");
			json.put("maxPrice", 0);
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = (String)request.getSession().getAttribute("id");
		int price = Integer.parseInt(request.getParameter("price"));
		MpriceDao dao = new MpriceDao();
		MpriceVo vo = dao.select(bnum);
		JSONObject json = new JSONObject();
		if(vo == null) {
			int n = dao.insert(new MpriceVo(0, bnum, id, price));
			if(n>0) {
				json.put("msg", "success");
			}else {
				json.put("msg", "오류로 인해 호가에 실패했습니다.");
			}
		}else{
			if(vo.getMaxprice() < price) {
				int n = dao.update(new MpriceVo(0, bnum, id, price));
				if(n>0) {
					json.put("msg", "success");
				}else {
					json.put("msg", "오류로 인해 호가에 실패했습니다.");
				}
			}else {
				json.put("msg", "현재 최고호가 이상의 금액을 입력하세요.");
			}
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
}
