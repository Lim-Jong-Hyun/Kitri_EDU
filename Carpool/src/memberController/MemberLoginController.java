package memberController;

import java.io.IOException;

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
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class MemberLoginController
 */
@WebServlet("/MemberLoginController")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginController() {
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
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberService mService = new MemberServiceImpl(new MemberDaoImpl());
		CarService cService = new CarServiceImpl(new CarDaoImpl());
		
		if(id == null || pwd == null) {
			return;
		}
		
		if(mService.login(id, pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("m", mService.getMember(id));
			Car c = cService.getCar(id);
			if(null != c) {
				session.setAttribute("c", c);
			}
		}
		response.sendRedirect(request.getContextPath() + "/content/main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
