package category.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.dao.CategoryDao;
import category.vo.CategoryVo;


@WebServlet("/Category.do")
public class CategoryController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		String mod = req.getParameter("mod");
		
		if(!mod.equals("") && mod.equals("list")) {
			list(req,res);
		}else if(!mod.equals("") && mod.equals("insert")) {
			insert(req,res);
		}else if(!mod.equals("") && mod.equals("update")) {
			update(req,res);
		}else if(!mod.equals("") && mod.equals("delete")) {
			delete(req,res);
		}else if(!mod.equals("") && mod.equals("getInfo")) {
			getInfo(req,res);
		}else if(!mod.equals("") && mod.equals("name_ck")) {//중복 카테고리 체크
			nameCk(req,res);
		}
		
		
	}

	private void nameCk(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/xml;charset=utf-8");
		String name=req.getParameter("cate_n");
		CategoryDao dao= CategoryDao.getInstance();
		int n = dao.nameCK(name);
		boolean ck=true;
		if(n>0) {
			ck=false;
		}
		PrintWriter pw=res.getWriter();
		pw.println("<?xml version='1.0' encoding='utf-8' ?>");
		pw.println("<result>");
		pw.println("<check>" + ck + "</check>");
		pw.println("</result>");
		pw.close();
		
	}

	private void insert(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException{
		String name=req.getParameter("cate_n");
		CategoryDao dao= CategoryDao.getInstance();
		int n = dao.addCategory(name);
		if(n>0) {
			res.sendRedirect("Category.do?mod=list");
		}else {
			req.setAttribute("errMsg", "오류로 인해 저장에 실패 했습니다.");
			req.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(req, res);
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	private void delete(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException{
		
		
	}

	private void getInfo(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException{
	
		
	}

	private void list(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
		CategoryDao dao= CategoryDao.getInstance();
		ArrayList<CategoryVo> list = new ArrayList<CategoryVo>();
		list=dao.list();
		req.setAttribute("C_list", list);
		req.getRequestDispatcher("/layout.jsp?page=/category/adminCate.jsp&left=admin.jsp").forward(req, res);
	}
}
