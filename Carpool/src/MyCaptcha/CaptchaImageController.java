package MyCaptcha;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import DesignPattern.PathInfo;

/**
 * Servlet implementation class CaptchaImageController
 */
@WebServlet("/CaptchaImageController")
public class CaptchaImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaImageController() {
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
		String key = CaptchaNkey();

		try {
			// Key값을 파싱해서 얻음. Key값이란 각 캡차의 이미지를 생성하기위해 구분자로 사용.
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(key);
			key = (String) jsonObj.get("key");
		} catch (Exception e) {
//			System.out.println("파싱안댐");
		}
		String img = CaptchaImage(key);

//		System.out.println("key는 " + key);
//		System.out.println("img는 " + img);

		// Index 페이지로 데이터 이동
		request.setAttribute("img", img);
		request.setAttribute("key", key);

		RequestDispatcher dispatcher = request.getRequestDispatcher("content/beforeJoin.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
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

	// 키 값을 네이버를 통해서 얻는 함수.
	public static String CaptchaNkey() {
		String nkey = "";
		String clientId = "ri4vEtl3RpdjfQ36bd_C";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "2U55SqradY";// 애플리케이션 클라이언트 시크릿값";
		try {
			String code = "0"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
			String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
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
			nkey = sb.toString();
		} catch (Exception e) {
		}

		return nkey;
	}

	// 캡차 이미지 수신
	public static String CaptchaImage(String key) {

		String clientId = "ri4vEtl3RpdjfQ36bd_C";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "2U55SqradY";// 애플리케이션 클라이언트 시크릿값";
		String img = "";
		try {
			// https://openapi.naver.com/v1/captcha/nkey
			// 호출로 받은 키값으로 이미지를 생성하는 과정
			String path = "C:\\Users\\KITRI\\Desktop\\workspace\\cp\\WebContent\\img\\";
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];

				// 랜덤한 이름으로 파일 생성
				String tempname = Long.valueOf(new Date().getTime()).toString();
				// System.out.println(tempname);
				File f = new File(path + tempname + ".jpg");
				f.createNewFile();
				OutputStream outputStream = new FileOutputStream(f);
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				is.close();
				outputStream.close();
				img = tempname + ".jpg";
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer sb = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}
				br.close();
//				System.out.println(sb.toString());
			}

		} catch (Exception e) {
//			System.out.println(e);
		}
		return img;
		// 이게 이미지이름이 됩니다.
	}

}
