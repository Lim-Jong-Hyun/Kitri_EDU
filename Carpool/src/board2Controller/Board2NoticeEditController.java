package board2Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.Board2;
import board2.Board2DaoImpl;
import board2.Board2Service;
import board2.Board2ServiceImpl;

/**
 * Servlet implementation class Board2NoticeEditController
 */
@WebServlet("/Board2NoticeEditController")
public class Board2NoticeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board2NoticeEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=EUC-KR");
		response.setCharacterEncoding("euc-kr");
		String view = "/Board2NoticeListController";
		Board2Service service = new Board2ServiceImpl(
				new Board2DaoImpl());
		Board2 b = new Board2(Integer.parseInt(request.getParameter("num")), 0, null, request.getParameter("title"), 
				request.getParameter("content"),request.getParameter("writer"));
		
		if(service.edit(b)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
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
