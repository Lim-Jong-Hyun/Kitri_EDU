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
 * Servlet implementation class Board1DriverRequestDelController
 */
@WebServlet("/Board1DriverRequestDelController")
public class Board1DriverRequestDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1DriverRequestDelController() {
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

		boolean change = false;

		if (b.getPassenger1().equals(m.getId())) {
			b.setPassenger1(b.getPassenger2());
			b.setPassenger2(b.getPassenger3());
			b.setPassenger3(null);
			b.setSeat(b.getSeat() - 1);
			change = true;
		} else if (b.getPassenger2().equals(m.getId())) {
			b.setPassenger2(b.getPassenger3());
			b.setPassenger3(null);
			b.setSeat(b.getSeat() - 1);
			change = true;
		} else if (b.getPassenger3().equals(m.getId())) {
			b.setPassenger3(null);
			b.setSeat(b.getSeat() - 1);
			change = true;
		}

		b = sortPassenger(b);

		if (bs.editPassenger(b) && change) {
			path = request.getContextPath() + "/Board1DriverDetailController?num=" + b.getNum();
			response.sendRedirect(path);
		} else {
			request.setAttribute("msg", "신청 취소에 실패하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}

	protected Board1 sortPassenger(Board1 b) {

		int seat = 0;

		if (b.getPassenger1() != null)
			seat++;
		if (b.getPassenger2() != null)
			seat++;
		if (b.getPassenger3() != null)
			seat++;

		if (b.getPassenger1() == null) {
			b.setPassenger1(b.getPassenger2());
			b.setPassenger2(b.getPassenger3());
			b.setPassenger3(null);
		}

		if (b.getPassenger2() == null) {
			b.setPassenger2(b.getPassenger3());
			b.setPassenger3(null);
		}

		b.setSeat(seat);

		return b;
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
