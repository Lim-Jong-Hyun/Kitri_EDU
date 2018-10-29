package carController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.CarDaoImpl;
import car.CarService;
import car.CarServiceImpl;
import member.Member;

/**
 * Servlet implementation class CarChkController
 */
@WebServlet("/CarChkController")
public class CarChkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarChkController() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=EUC-KR");

		HttpSession session = request.getSession(false);
		CarService cService = new CarServiceImpl(new CarDaoImpl());
		Member m = (Member) session.getAttribute("m");

		if (m == null) {
			// 로그인 세션이 제대로 있는지 확인
			request.setAttribute("msg", "세션이 만료되었습니다. 로그인해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} else if (session.getAttribute("c") != null || cService.getCar(m.getId()) != null) {
			// 차량이 등록되어있는지 체크
			request.setAttribute("msg", "이미 차량이 등록되어있습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} else if (m.getType() != 1) {
			// 타입이 운전자인지 체크
			request.setAttribute("msg", "차량등록이 가능한 유저가 아닙니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("chk", true);
			RequestDispatcher rd = request.getRequestDispatcher("/content/carAdd.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}
}