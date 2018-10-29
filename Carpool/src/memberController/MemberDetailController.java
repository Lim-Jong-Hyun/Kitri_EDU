package memberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.CarDaoImpl;
import car.CarService;
import car.CarServiceImpl;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class MemberDetailConroller
 */
@WebServlet("/MemberDetailController")
public class MemberDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberDetailController() {
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
		String id = request.getParameter("id");
		MemberService ms = new MemberServiceImpl(new MemberDaoImpl());
		CarService cs = new CarServiceImpl(new CarDaoImpl());

		Member m = ms.getMember(id);

		request.setAttribute("m", m);
		
		if(m.getType() == 1 && cs.getCar(m.getId()) != null) {
			request.setAttribute("chkCar", true);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/content/memberDetail.jsp");
		if (rd != null) {
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
