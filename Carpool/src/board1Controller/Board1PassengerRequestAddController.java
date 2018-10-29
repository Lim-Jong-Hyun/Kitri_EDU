package board1Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board1.Board1;
import board1.Board1DaoImpl;
import board1.Board1Service;
import board1.Board1ServiceImpl;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class Board1DriverRequestAddController
 */
@WebServlet("/Board1PassengerRequestAddController")
public class Board1PassengerRequestAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1PassengerRequestAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		response.setContentType("text/html, charset=EUC-KR");

		Board1Service bs = new Board1ServiceImpl(new Board1DaoImpl());
		MemberService ms = new MemberServiceImpl(new MemberDaoImpl());
		HttpSession session = request.getSession(false);
		int num = Integer.parseInt(request.getParameter("num"));
		Board1 b = bs.getByNum(num);
		String path = "";

		if (session.getAttribute("m") == null) {
			request.setAttribute("msg", "세션이 만료되었습니다. 로그인을 해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}

		Member m = ms.getMember(((Member) session.getAttribute("m")).getId());

		
		if(b.getSeat() < 1) {
			b.setDriver(m.getId());

			if(b.getDriver().equals(""))
				b.setDriver(null);
			
			if (b.getDriver() == null)
				b.setSeat(0);
			else
				b.setSeat(1);
			
			
		} else {
			request.setAttribute("msg", "자리가 없습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}

		if (bs.editDriver(b)) {
			path = request.getContextPath() + "/Board1PassengerDetailController?num=" + b.getNum();
			response.sendRedirect(path);
		} else {
			request.setAttribute("msg", "신청에 실패하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
