package board.controll;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.boardDao;
import board.vo.boardVo;

@WebServlet("/insertBoard.do")
public class newBoaradController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("text/plain;charset=utf-8");
		String mod = req.getParameter("mod");
		if (!mod.equals("") && mod.equals("insert")) {
			insert(req, res);// insert newBoard
		} else if (!mod.equals("") && mod.equals("update")) {
			update(req, res);// update board
		}

	}

	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String  Sbnum= req.getParameter("boardNum");
		int bnum=0;
		if(Sbnum!=null) {
			System.out.println(Sbnum);
			bnum=Integer.parseInt(Sbnum);
		}
		
		String path = getServletContext().getRealPath("/upload/");
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
		java.util.Date cu = new java.util.Date();
		String addPath = dateForm.format(cu);
		path += addPath;
		// 날짜 경로 추가
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		MultipartRequest data = new MultipartRequest(req, path, 1024 * 1024 * 10, "utf-8",
				new DefaultFileRenamePolicy());
		String title = data.getParameter("title");
		int cate = Integer.parseInt(data.getParameter("cate"));
		String content = data.getParameter("content");
		String orgfilename = data.getOriginalFileName("orgfile");
		String savefilename = addPath + "/" + data.getFilesystemName("orgfile");
		int startprice = Integer.parseInt(data.getParameter("price"));
		String sdate = data.getParameter("sdate");
		String hh = data.getParameter("hh");
		String mm = data.getParameter("mm");
		String starttime = sdate.substring(0, 4) + "" + sdate.substring(4, 6) + "" + sdate.substring(6, 8) + "" + hh
				+ "" + mm + "00";
		// DB SAVE
				boardDao dao = boardDao.getInstance();
				boardVo vo = new boardVo(bnum, null, cate, title, content, orgfilename, savefilename, starttime, startprice, 0,
						0, 0, null);
				int n = dao.update(vo);
				if (n > 0) {
					res.sendRedirect("/detail.do?cmd=detail&bnum="+bnum);
				} else {
					req.setAttribute("errMsg", "오류로 인해 저장에 실패 했습니다.");
					req.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(req, res);
				}
	}

	private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = getServletContext().getRealPath("/upload/");
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
		java.util.Date cu = new java.util.Date();
		String addPath = dateForm.format(cu);
		path += addPath;
		// 날짜 경로 추가
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		MultipartRequest data = new MultipartRequest(req, path, 1024 * 1024 * 10, "utf-8",
				new DefaultFileRenamePolicy());
		String id = data.getParameter("id");
		String title = data.getParameter("title");
		int cate = Integer.parseInt(data.getParameter("cate"));
		String content = data.getParameter("content");
		String orgfilename = data.getOriginalFileName("orgfile");
		String savefilename = addPath + "/" + data.getFilesystemName("orgfile");
		int startprice = Integer.parseInt(data.getParameter("price"));
		String sdate = data.getParameter("sdate");
		String hh = data.getParameter("hh");
		String mm = data.getParameter("mm");
		String starttime = sdate.substring(0, 4) + "" + sdate.substring(4, 6) + "" + sdate.substring(6, 8) + "" + hh
				+ "" + mm + "00";
		// DB SAVE
		boardDao dao = boardDao.getInstance();
		boardVo vo = new boardVo(0, id, cate, title, content, orgfilename, savefilename, starttime,  startprice, 0,
				0, 0, null);
		int n = dao.insert(vo);
		if (n > 0) {
			res.sendRedirect("/HTA-semi/boardList.do?mod=list");
		} else {
			req.setAttribute("errMsg", "오류로 인해 저장에 실패 했습니다.");
			req.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(req, res);
		}

	}
}
