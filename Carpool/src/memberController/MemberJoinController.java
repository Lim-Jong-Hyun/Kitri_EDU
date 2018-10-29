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
 * Servlet implementation class JoinController
 */
@MultipartConfig(location = "C:\\temp", maxFileSize = -1)
@WebServlet("/MemberJoinController")
public class MemberJoinController extends HttpServlet {
	private PathInfo pi;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberJoinController() {
		super();
		pi = PathInfo.getInstance();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=EUC-KR");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int type = Integer.parseInt(request.getParameter("type"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		int isSmoke = Integer.parseInt(request.getParameter("isSmoke"));

		Part file = request.getPart("profile");
		
		MemberService ms = new MemberServiceImpl(new MemberDaoImpl());

		String path = "";

		if (file != null && file.getSize() > 0) {
			String[] header = file.getHeader("Content-Disposition").split(";");
			for (String a : header) {
				if (a.trim().startsWith("filename")) {
					path = a.substring(a.lastIndexOf("."), a.length() - 1).trim();
				}
			}
		}

		if (path.equals("")) {
			path = "default_profile.jpg";
		} else {
			path = id + "_profile" + path;
		}

		long size = file.getSize();
		if (size > 0) {
			file.write(pi.getPath() + path);
		}

		Member m = new Member(id, pwd, name, email, tel, path, type, sex, isSmoke);
		boolean flag = ms.join(m);
		if (flag) {
			request.setAttribute("msg", "회원가입이 완료되었습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}