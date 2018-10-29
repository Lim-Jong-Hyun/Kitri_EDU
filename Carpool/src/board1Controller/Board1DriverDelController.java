package board1Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.Board1DaoImpl;
import board1.Board1Service;
import board1.Board1ServiceImpl;

/**
 * Servlet implementation class Board1DriverDelController
 */
@WebServlet("/Board1DriverDelController")
public class Board1DriverDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board1DriverDelController() {
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
		String view = "/Board1DriverListController";
		Board1Service service = new Board1ServiceImpl(
				new Board1DaoImpl());
		int num = Integer.parseInt(request.getParameter("num"));
		if(service.remove(num)) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(view);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
			
		} else {
			request.setAttribute("msg", "존재하지 않는 게시글입니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
			if (rd != null) {
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
