package controller.ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.ms.CommentsDao;
import vo.ms.CommentsVo;

@WebServlet("/comm.do")
public class CommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd != null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd != null && cmd.equals("list")) {
			list(request,response);
		}
		
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = request.getParameter("id");
		String comm = request.getParameter("comm");
		
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentsDao dao = new CommentsDao();
		ArrayList<CommentsVo> list = dao.list();
		JSONObject json = new JSONObject();
		json.put("list", list);
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	}
}
