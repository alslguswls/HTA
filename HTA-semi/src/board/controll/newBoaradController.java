package board.controll;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.boardDao;
import board.vo.boardVo;
@WebServlet("/insertBoard.do")
public class newBoaradController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = "/HTA-semi/upload/";
		
		MultipartRequest data=new MultipartRequest(
				req, 
				path,
				1024*1024*10,
				new DefaultFileRenamePolicy()
				);
		String id= data.getParameter("id");
		String title= data.getParameter("title");
		int cate= Integer.parseInt(data.getParameter("cate"));
		String content= data.getParameter("content");
		String orgfilename= data.getOriginalFileName("orgfile");
		String savefilename=data.getFilesystemName("orgfile");
		int startprice=Integer.parseInt(data.getFilesystemName("price"));
		String sdate = data.getParameter("sdate");
		String hh =  data.getParameter("hh");
		String mm =  data.getParameter("mm");
		String from =sdate.substring(0, 4) + "-" + sdate.substring(4,6)+"-"+sdate.substring(6,8)+" "+hh+":"+mm;
		SimpleDateFormat transFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date starttime = null;
		try {
			starttime = (Date) transFormat.parse(from);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		boardDao dao= boardDao.getInstance();
		boardVo vo=new boardVo(0,id,cate,title,content,orgfilename,savefilename,starttime,startprice,0,0,0,null);
		int n=dao.insert(vo);
		if(n>0){
			
		}else{
			
		}	  
		
	}
}
