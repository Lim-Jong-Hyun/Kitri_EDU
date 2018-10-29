package board1Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.Board1;
import board1.Board1DaoImpl;
import board1.Board1Service;
import board1.Board1ServiceImpl;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class Board1DriverEditController
 */
@WebServlet("/Board1DriverEditController")
public class Board1DriverEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1DriverEditController() {
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

		Board1Service service = new Board1ServiceImpl(new Board1DaoImpl());
		MemberService mservice = new MemberServiceImpl(new MemberDaoImpl());

		int num = Integer.parseInt(request.getParameter("num"));
		String cate = request.getParameter("cate");
		String startTime = request.getParameter("startTime");
		String startPosi = request.getParameter("startPosi");
		String endPosi = request.getParameter("endPosi");
		int price = Integer.parseInt(request.getParameter("price"));
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String passenger1 = request.getParameter("passenger1");
		String passenger2 = request.getParameter("passenger2");
		String passenger3 = request.getParameter("passenger3");
		int maxSeat = Integer.parseInt(request.getParameter("maxSeat"));
		int seat = Integer.parseInt(request.getParameter("seat"));
		String path = "";

		Double s_x = Double.valueOf(request.getParameter("spx"));
		Double s_y = Double.valueOf(request.getParameter("spy"));
		Double e_x = Double.valueOf(request.getParameter("epx"));
		Double e_y = Double.valueOf(request.getParameter("epy"));
		String writer = request.getParameter("writer");

		Member m = mservice.getMember(writer);
		String profile = m.getProfile();
		
		if(passenger1.equals(""))
			passenger1 = null;
		if(passenger2.equals(""))
			passenger2 = null;
		if(passenger3.equals(""))
			passenger3 = null;
		
		
		Board1 b = new Board1(num, 0, cate, null, startTime, startPosi, endPosi, s_x, s_y, e_x, e_y, price, content,
				title, seat, maxSeat, writer, writer, passenger1, passenger2, passenger3, profile);

		b = sortPassenger(b);

		if (service.edit(b)) {
			path = request.getContextPath() + "/Board1DriverListController";
			response.sendRedirect(path);
		} else {
			request.setAttribute("msg", "글수정에 실패하였습니다.");
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
