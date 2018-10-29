package board1Controller;

import java.io.IOException;
import java.util.ArrayList;

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

/**
 * Servlet implementation class Board1PartnerListController
 */
@WebServlet("/Board1PartnerListController")
public class Board1PartnerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1PartnerListController() {
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
		response.setContentType("text/html; charset=EUC-KR");

		Board1Service service = new Board1ServiceImpl(new Board1DaoImpl());
		HttpSession session = request.getSession(false);
		// 로그인한 사람 멤버객채
		Member m = (Member) session.getAttribute("m");

		// 로그인한 사람이 포함된 글목록
		ArrayList<Board1> list1 = new ArrayList<Board1>();

		// JSP로 던질 정보
		ArrayList<Board1> list2 = new ArrayList<Board1>();

		String d;
		String p1;

		if (m.getType() == 1) {
			// 드라이버로 로그인해서 파트너 버튼 눌렀을때
			list1 = service.getByDriver(m.getId());
			for (int i = 0; i < list1.size(); i++) {
				p1 = list1.get(i).getPassenger1();
				if (p1 != null) {
					list2.add(list1.get(i));
				}
			}
		} else if (m.getType() == 2) {
			// 패신저1,2,3중에 하나라도 포함되면 list1에 넣는다
			list1 = service.getByPassenger(m.getId());
			for (int i = 0; i < list1.size(); i++) {
				d = list1.get(i).getDriver();
				if (d != null) {
					list2.add(list1.get(i));
				}
			}
		} else {
			// 관리자로

		}

		request.setAttribute("list", list2);
		RequestDispatcher rd = request.getRequestDispatcher("/content/partner.jsp");
		if (null != rd) {
			rd.forward(request, response);
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
