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
			// �α��� ������ ����� �ִ��� Ȯ��
			request.setAttribute("msg", "������ ����Ǿ����ϴ�. �α������ּ���.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} else if (session.getAttribute("c") != null || cService.getCar(m.getId()) != null) {
			// ������ ��ϵǾ��ִ��� üũ
			request.setAttribute("msg", "�̹� ������ ��ϵǾ��ֽ��ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} else if (m.getType() != 1) {
			// Ÿ���� ���������� üũ
			request.setAttribute("msg", "��������� ������ ������ �ƴմϴ�.");
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