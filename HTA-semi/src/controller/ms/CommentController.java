package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.ms.CommentsDao;
import dao.ms.ReservationDao;
import vo.ms.CommentsVo;
import vo.ms.ReservationVo;

@WebServlet("/comm.do")
public class CommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd != null && cmd.equals("list")) {
			list(request,response);
		}
		
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scnum = request.getParameter("cnum");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = request.getParameter("id");
		String comm = request.getParameter("comm");
		int cnum = 0;
		int ref = 0;
		int lev = 0;
		int step = 0;
		if(scnum != null && !scnum.equals("")) {
			cnum = Integer.parseInt(scnum);
			ref = Integer.parseInt(request.getParameter("ref"));
			lev = Integer.parseInt(request.getParameter("lev"));
			step = Integer.parseInt(request.getParameter("step"));
		}
		CommentsDao dao = new CommentsDao();
		int n = dao.insert(new CommentsVo(cnum, bnum, id, comm, ref, lev, step));
		JSONObject json = new JSONObject();
		if(n>0) {
			json.put("result", true);
		}else {
			json.put("result", false);
		}
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("bnum"));
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if(spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum-1)*10+1;
		int endRow = startRow+9;
		CommentsDao dao = new CommentsDao();
		ArrayList<CommentsVo> list = dao.list(num, startRow, endRow);
		
		int pageCount = (int)Math.ceil(dao.getCount()/10.0);
		int startPage = (pageNum-1)/10*10+1;
		int endPage = startPage+9;
		if(endPage>pageCount) endPage=pageCount;
		
//		request.setAttribute("list", list);
//		request.setAttribute("pageNum", pageNum);
//		request.setAttribute("pageCount", pageCount);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
		
		JSONArray arr = new JSONArray();
		for(CommentsVo vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("cnum", vo.getCnum());
			obj.put("bnum", vo.getBnum());
			obj.put("id", vo.getId());
			obj.put("comments", vo.getComments());
			obj.put("ref", vo.getRef());
			obj.put("lev", vo.getLev());
			obj.put("step", vo.getStep());
			arr.add(obj);
		}
		JSONObject json = new JSONObject();
		json.put("list", arr);
		json.put("pageNum", pageNum);
		json.put("pageCount", pageCount);
		json.put("startPage", startPage);
		json.put("endPage", endPage);
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
}
