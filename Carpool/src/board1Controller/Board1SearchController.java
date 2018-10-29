package board1Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.Board1;
import board1.Board1DaoImpl;
import board1.Board1Service;
import board1.Board1ServiceImpl;

/**
 * Servlet implementation class Board1SearchController
 */
@WebServlet("/Board1SearchController")
public class Board1SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1SearchController() {
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
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=EUC-KR");

		Board1Service service = new Board1ServiceImpl(new Board1DaoImpl());

		String start_posi = request.getParameter("startPosi");
		String end_posi = request.getParameter("endPosi");
		int type = Integer.parseInt(request.getParameter("t"));
		String path = "";
		ArrayList<Board1> list1 = service.getByStartPosi(start_posi);
		ArrayList<Board1> list2 = service.getByEndPosi(end_posi);
		ArrayList<Board1> list3 = service.getByStartEnd(start_posi, end_posi);
		ArrayList<Board1> list = new ArrayList<Board1>();

		if (type == 0) {
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getType() == 1) {
					list1.remove(i);
					i--;
				}
			}
			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).getType() == 1) {
					list2.remove(i);
					i--;
				}
			}
			for (int i = 0; i < list3.size(); i++) {
				if (list3.get(i).getType() == 1) {
					list3.remove(i);
					i--;
				}
			}
			path = "/content/driver.jsp";
		} else if (type == 1) {
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getType() == 0) {
					list1.remove(i);
					i--;
				}
			}
			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).getType() == 0) {
					list2.remove(i);
					i--;
				}
			}
			for (int i = 0; i < list3.size(); i++) {
				if (list3.get(i).getType() == 0) {
					list3.remove(i);
					i--;
				}
			}
			path = "/content/passenger.jsp";
		}
		
		if (list1.size() != 0 && list2.size() == 0) {
			list = list1;
		} else if (list1.size() == 0 && list2.size() != 0) {
			list = list2;
		} else if (list1.size() != 0 && list2.size() != 0) {
			list = list3;
		}

		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		if (null != rd) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
