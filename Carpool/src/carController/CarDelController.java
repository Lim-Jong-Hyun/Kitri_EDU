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
import member.MemberDaoImpl;
import member.MemberService;
import member.MemberServiceImpl;

/**
 * Servlet implementation class CarDelController
 */
@WebServlet("/CarDelController")
public class CarDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarDelController() {
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
		
		Member m = (Member)session.getAttribute("m");
		m = ms.getMember(m.getId());
		
		if(session.getAttribute("m") == null) {
			request.setAttribute("msg", "세션이 만료되었습니다. 로그인 해주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		} else if(session.getAttribute("c") == null) {
			request.setAttribute("msg", "등록된 차량이 없습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if(rd != null) {
				rd.forward(request, response);
			}
		} else {
			if(cs.delCar(m.getId())) {
				session.removeAttribute("c");
				response.sendRedirect(request.getContextPath() + "/content/main.jsp");
			} else {
				request.setAttribute("msg", "차량삭제에 실패하였습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
				if(rd != null) {
					rd.forward(request, response);
				}
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
