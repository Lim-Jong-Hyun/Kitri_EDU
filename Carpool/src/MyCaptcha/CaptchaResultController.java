package MyCaptcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class CapchaResultController
 */
@WebServlet("/CaptchaResultController")
public class CaptchaResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaResultController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// index 페이지에서 가져온 값
		String key = (String) request.getParameter("key");
		String input = (String) request.getParameter("input");

		// 이미지 값과 입력한 값의 비교
		// 맞으면 true 아니면 false
//		System.out.println(input);
		String result = CaptchaNkeyResult(key, input);
		boolean b = false;

		// true or false에 따라 이동할 페이지경로
		String path = "";

		try {
			// result 또한 JSON형식이므로 파싱 하여 boolean값 b에 할당
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
			b = (boolean) jsonObj.get("result");
		} catch (Exception e) {
//			System.out.println("파싱안댐");
		}
		request.setAttribute("result", b);
		request.setAttribute("check", 1);
		if (b == true) {
			path = "content/join.jsp";
			request.setAttribute("chk", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		} else {
			path = "CaptchaImageController";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		}
	}

	public static String CaptchaNkeyResult(String key, String value) {
		String result = "";
		String clientId = "ri4vEtl3RpdjfQ36bd_C";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "2U55SqradY";// 애플리케이션 클라이언트 시크릿값";
		try {
			String code = "1"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
			// 캡차 키 발급시 받은 키값
			// 사용자가 입력한 캡차 이미지 글자값
			String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value="
					+ value;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
//			System.out.println(sb.toString());
			result = sb.toString();
		} catch (Exception e) {
//			System.out.println(e);
		}

		return result;
	}

}
