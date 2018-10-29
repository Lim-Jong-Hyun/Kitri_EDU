package memberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DesignPattern.PathInfo;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class MemberEditController
 */
@MultipartConfig(location = "C:\\temp", maxFileSize = -1)
@WebServlet("/MemberEditController")
public class MemberEditController extends HttpServlet {
	private PathInfo pi;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEditController() {
        super();
        pi = PathInfo.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int isSmoke = Integer.parseInt(request.getParameter("isSmoke"));

		Part file = request.getPart("profile");

		String path = "";
		
		MemberService ms = new MemberServiceImpl(new MemberDaoImpl());
		Member db_m = ms.getMember(id);
		
		if(oldPwd.equals(db_m.getPw())) {
			if(!newPwd.equals("")) {
				oldPwd = newPwd;
			}
			
			if (file != null && file.getSize() > 0) {
				String[] header = file.getHeader("Content-Disposition").split(";");
				for (String a : header) {
					if (a.trim().startsWith("filename")) {
						path = a.substring(a.lastIndexOf("."), a.length() - 1).trim();
					}
				}
			}

			if (path.equals("")) {
				path = db_m.getProfile();
			} else {
				path = id + "_profile" + path;
			}

			long size = file.getSize();
			if (size > 0) {
				file.write(pi.getPath() + path);
			}
		} else {
			request.setAttribute("msg", "비밀번호가 틀립니다. 회원정보 수정이 실패했습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}

		Member m = new Member(id, oldPwd, name, email, tel, path, db_m.getType(), db_m.getSex(), isSmoke);
		boolean flag = ms.editInfo(m);
		
		if (flag) {
			request.setAttribute("msg", "회원정보 수정이 완료되었습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
