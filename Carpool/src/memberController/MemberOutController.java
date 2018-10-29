package memberController;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DesignPattern.PathInfo;
import car.CarDaoImpl;
import car.CarService;
import car.CarServiceImpl;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class MemberOutController
 */
@WebServlet("/MemberOutController")
public class MemberOutController extends HttpServlet {
	
	private PathInfo pi;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberOutController() {
        super();
        pi = PathInfo.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=EUC-KR");
		
		HttpSession session = request.getSession(false);
		MemberService mService = new MemberServiceImpl(new MemberDaoImpl());
		CarService cService = new CarServiceImpl(new CarDaoImpl());
		
		if(session.getAttribute("m") == null) {
			request.setAttribute("msg", "로그인 세션이 만료되었습니다. 로그인해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}
		
		Member m = (Member) session.getAttribute("m");
		String id = m.getId();
		String pwd = request.getParameter("pwd");
		
		if(mService.getMember(id).getPw().equals(pwd)) {
			session.removeAttribute("m");
			session.removeAttribute("c");
			session.invalidate();

			cService.delCar(id);
			mService.out(id, pwd);
			
			if(!m.getProfile().equals("default_profile.jpg")) {
				File f = new File(pi.getPath() + m.getProfile());
				f.delete();
			}
			
			request.setAttribute("msg", "회원탈퇴가 완료되었습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("msg", "비밀번호가 틀립니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}
		
		//response.sendRedirect(request.getContextPath() + "/content/main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
