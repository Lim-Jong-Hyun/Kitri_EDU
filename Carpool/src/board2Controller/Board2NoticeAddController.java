package board2Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board2.Board2;
import board2.Board2DaoImpl;
import board2.Board2Service;
import board2.Board2ServiceImpl;

/**
 * Servlet implementation class Board2NoticeAddController
 */
@WebServlet("/Board2NoticeAddController")
public class Board2NoticeAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board2NoticeAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//글작성
	request.setCharacterEncoding("euc-kr");
	response.setContentType("text/html; charset=EUC-KR");
	response.setCharacterEncoding("euc-kr");
	String view = "/Board2NoticeListController";
	Board2Service service = new Board2ServiceImpl(
			new Board2DaoImpl());
	HttpSession session = request.getSession(false);

	if (session.getAttribute("m") == null) {
		request.setAttribute("msg", "세션이 만료되었습니다. 로그인을 해주세요.");
		RequestDispatcher rd = request.getRequestDispatcher("/content/msg.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}
	Board2 b = new Board2(0, 0, null, request.getParameter("title"), 
			request.getParameter("content"),request.getParameter("writer"));
	service.add(b);
	RequestDispatcher dispatcher = 
			request.getRequestDispatcher(view);
	if (dispatcher != null) {
		dispatcher.forward(request, response);
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
