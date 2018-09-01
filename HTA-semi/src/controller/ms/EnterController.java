package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.ms.BoardDao;
import dao.ms.ChatDao;
import dao.ms.MpriceDao;
import dao.ms.ReservationDao;
import dao.ms.ResultDao;
import dao.ms.UsersDao;
import vo.ms.BoardVo;
import vo.ms.ChatVo;
import vo.ms.CommentsVo;
import vo.ms.MpriceVo;
import vo.ms.ReservationVo;
import vo.ms.ResultVo;
import vo.ms.UsersVo;

@WebServlet("/enter.do")
public class EnterController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("resv")) {
			resv(request,response);
		}else if(cmd != null && cmd.equals("resvOk")) {
			resvOk(request, response);
		}else if(cmd != null && cmd.equals("timer")) {
			timer(request, response);
		}else if(cmd != null && cmd.equals("call")) {
			call(request, response);
		}else if(cmd != null && cmd.equals("chat")) {
			chat(request, response);
		}else if(cmd != null && cmd.equals("road")) {
			road(request, response);
		}else if(cmd != null && cmd.equals("end")) {
			end(request, response);
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
	protected void resvOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		ArrayList<CategoryVo> list1 = leftList();
		request.setAttribute("list1", list1);
		request.getRequestDispatcher("layout.jsp?page=enter.jsp?bnum="+bnum).forward(request, response);
	}
	protected void timer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao bdao = new BoardDao();
		BoardVo bvo = bdao.detail(bnum);
		int status = bvo.getStatus();
		String starttime = bvo.getStarttime();
		String[] day = starttime.substring(0,10).split("-");
		int year = Integer.parseInt(day[0]);
		int month = Integer.parseInt(day[1]);
		int date = Integer.parseInt(day[2]);
		int hourOfDay = Integer.parseInt(starttime.substring(11,13));
		int minute = Integer.parseInt(starttime.substring(14,16));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date, hourOfDay, minute, 0);
		Calendar sysdate = Calendar.getInstance();
		long more10 = cal.getTimeInMillis() + (10*60*1000);
		long time = (more10 - sysdate.getTimeInMillis()) / 1000L;
		
		JSONObject json = new JSONObject();
		json.put("time", time);
		
		MpriceDao dao = new MpriceDao();
		MpriceVo vo = dao.select(bnum);
		
		if(vo != null) {
			json.put("id", vo.getId());
			json.put("maxPrice", vo.getMaxprice());
		}else {
			json.put("id", "시작가");
			json.put("maxPrice", bvo.getStartprice());
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = (String)session.getAttribute("id");
		int price = Integer.parseInt(request.getParameter("price"));
		MpriceDao dao = new MpriceDao();
		MpriceVo vo = dao.select(bnum);
		BoardDao bdao = new BoardDao();
		BoardVo bvo = bdao.detail(bnum);
		int status = bvo.getStatus();
		UsersDao udao = new UsersDao();
		UsersVo uvo = udao.select(id);
		JSONObject json = new JSONObject();
		if(status == 2) {
			json.put("msg", "경매가 종료되어 호가에 실패했습니다..");
		}else {
			if(uvo.getCoin() >= price) {
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
			}else {
				json.put("msg", "보유 coin이상의 호가는 불가합니다.");
			}
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void chat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = (String)request.getSession().getAttribute("id");
		String msg = request.getParameter("msg");
		ChatDao dao = new ChatDao();
		int n = dao.insert(new ChatVo(0, bnum, id, msg, 0));
		JSONObject json = new JSONObject();
		if(n>0) {
			json.put("msg", "success");
		}else {
			json.put("msg", "오류로 인해 전송에 실패했습니다.");
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void road(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		ChatDao dao = new ChatDao();
		ArrayList<ChatVo> list = dao.list(bnum);
		JSONArray arr = new JSONArray();
		for(ChatVo vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("chat_no", vo.getChat_no());
			obj.put("bnum", vo.getBnum());
			obj.put("id", vo.getId());
			obj.put("str", vo.getStr());
			obj.put("status", vo.getStatus());
			arr.add(obj);
		}
		JSONObject json = new JSONObject();
		if(list != null) {
			json.put("list", arr);
		}else {
			json.put("msg", "오류로 인해 채팅 불러오기를 실패하였습니다.");
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		MpriceDao dao = new MpriceDao();
		MpriceVo vo = dao.select(bnum);
		JSONObject json = new JSONObject();
		if(vo != null) {
			ResultDao rdao = new ResultDao();
			ResultVo rvo = rdao.select(bnum);
			if(rvo == null) {
				int n = rdao.insert(new ResultVo(0, bnum, vo.getId(), vo.getMaxprice(), new Date()));
				if(n>0) {
					UsersDao udao = new UsersDao();
					int n1 = udao.coinUpdate(vo.getId(), -(vo.getMaxprice()));
					if(n1>0) {
						BoardDao bdao = new BoardDao();
						BoardVo bvo = bdao.detail(bnum);
						udao.coinUpdate(bvo.getId(), vo.getMaxprice());
					}
					end(request, response);
					return;
				}
			}
			json.put("id", rvo.getId());
			json.put("price", rvo.getPrice());
		}else {
			json.put("id", "낙찰자 없음");
			json.put("price", "-");
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	private ArrayList<CategoryVo> leftList(){
		CategoryDao dao= CategoryDao.getInstance();
		ArrayList<CategoryVo> list = new ArrayList<CategoryVo>();
		list=dao.leftList();
		return list;	
	}
}
