package board.controll;

import java.io.IOException;

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
			
		String path = getServletContext().getRealPath("/upload");
			MultipartRequest data = new MultipartRequest(
					req, 
					path, 
					1024 * 1024 * 10,
					"utf-8",
					new DefaultFileRenamePolicy()
			);
			String id = data.getParameter("id");
			String title = data.getParameter("title");
			int cate = Integer.parseInt(data.getParameter("cate"));
			String content = data.getParameter("content");
			String orgfilename = data.getOriginalFileName("orgfile");
			String savefilename = data.getFilesystemName("orgfile");
			int startprice = Integer.parseInt(data.getParameter("price"));
			String sdate = data.getParameter("sdate");
			String hh = data.getParameter("hh");
			String mm =data.getParameter("mm");
			String starttime = sdate.substring(0, 4)+"/"+sdate.substring(4, 6)+"/"+sdate.substring(6, 8)+" "+hh+":"+mm+":00";
			//DB SAVE
			boardDao dao = boardDao.getInstance();
			boardVo vo = new boardVo(0, id, cate, title, content, orgfilename, savefilename, starttime,null, startprice, 0,
					0, 0, null);
			int n = dao.insert(vo);
			if (n > 0) {
				res.sendRedirect("/HTA-semi/boardList.do");
			} else {
				req.setAttribute("errMsg", "오류로 인해 저장에 실패 했습니다.");
				req.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(req, res);
			}
		} 
	}

