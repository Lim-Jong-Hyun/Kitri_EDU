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
@WebServlet("/Board1PassengerEditController")
public class Board1PassengerEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1PassengerEditController() {
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
		String driver = request.getParameter("driver");
		String writer = request.getParameter("writer");
		String path = "";

		Double s_x = Double.valueOf(request.getParameter("spx"));
		Double s_y = Double.valueOf(request.getParameter("spy"));
		Double e_x = Double.valueOf(request.getParameter("epx"));
		Double e_y = Double.valueOf(request.getParameter("epy"));

		Member m = mservice.getMember(writer);
		String profile = m.getProfile();
				
		Board1 b = new Board1(num, 1, cate, null, startTime, startPosi, endPosi, s_x, s_y, e_x, e_y, price, content,
				title, 0, 1, writer, driver, writer, null, null, profile);

		if(b.getDriver().equals(""))
			b.setDriver(null);
		
		if (b.getDriver() == null)
			b.setSeat(0);
		else
			b.setSeat(1);

		if (service.edit(b)) {
			path = request.getContextPath() + "/Board1PassengerListController";
			response.sendRedirect(path);
		} else {
			request.setAttribute("msg", "글수정에 실패하였습니다.");
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
