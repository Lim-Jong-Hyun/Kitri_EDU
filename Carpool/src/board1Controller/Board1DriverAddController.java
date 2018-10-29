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
import member.MemberService;
import member.MemberServiceImpl;
import member.MemberDaoImpl;
import member.Member;

/**
 * Servlet implementation class Board1DriverAddController
 */
@WebServlet("/Board1DriverAddController")
public class Board1DriverAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1DriverAddController() {
		super();
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
		response.setContentType("text/html, charset=EUC-KR");

		Board1Service service = new Board1ServiceImpl(new Board1DaoImpl());
		MemberService mservice = new MemberServiceImpl(new MemberDaoImpl());

		HttpSession session = request.getSession(false);

		if (session.getAttribute("m") == null) {
			request.setAttribute("msg", "세션이 만료되었습니다. 로그인을 해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
		String cate = request.getParameter("cate");
		String startTime = request.getParameter("startTime");
		String startPosi = request.getParameter("startPosi");
		String endPosi = request.getParameter("endPosi");
		int price = Integer.parseInt(request.getParameter("price"));
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		int maxSeat = Integer.parseInt(request.getParameter("maxSeat"));
		int seat = 0;
		String path = "";

		Double s_x = Double.valueOf(request.getParameter("spx"));
		Double s_y = Double.valueOf(request.getParameter("spy"));
		Double e_x = Double.valueOf(request.getParameter("epx"));
		Double e_y = Double.valueOf(request.getParameter("epy"));
		Member m = mservice.getMember( ((Member)session.getAttribute("m")).getId() );
		String profile = m.getProfile();
		Board1 b = new Board1(0, 0, cate, null, startTime, startPosi, endPosi, s_x, s_y, e_x, e_y, price, content, title, seat, maxSeat, m.getId(), m.getId(), null, null, null, profile);

		if(service.add(b)) {
			path = request.getContextPath() + "/Board1DriverListController";
			response.sendRedirect(path);
		} else {
			request.setAttribute("msg", "글쓰기에 실패하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
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
