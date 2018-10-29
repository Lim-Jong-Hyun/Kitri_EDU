package carController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.Car;
import car.CarDaoImpl;
import car.CarService;
import car.CarServiceImpl;
import member.Member;
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class CarAddController
 */
@WebServlet("/CarAddController")
public class CarAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarAddController() {
        super();
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
		
		CarService cs = new CarServiceImpl(new CarDaoImpl());
		MemberService ms = new MemberServiceImpl(new MemberDaoImpl());
		HttpSession session = request.getSession(false);

		if (session.getAttribute("m") == null) {
			request.setAttribute("msg", "세션이 만료되었습니다. 로그인을 해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
		
		Member m = (Member)session.getAttribute("m");
		
		m = ms.getMember(m.getId());
		if(cs.insertCar(new Car(
				m.getId(),
				request.getParameter("license"),
				request.getParameter("numberPlate"),
				Integer.parseInt(request.getParameter("mileage")),
				request.getParameter("age"),
				request.getParameter("carName"),
				Integer.parseInt(request.getParameter("carSize"))))) {

			request.setAttribute("msg", "차량등록을 완료하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			session.setAttribute("c", cs.getCar(m.getId()));
			if(rd != null) {
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("msg", "차량등록을 실패하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
