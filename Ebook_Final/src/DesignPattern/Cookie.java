package DesignPattern;

public class Cookie {
	private static Cookie ck = new Cookie();
	private String cookieId = "";
	private int cookieNum;

	public String getCookieId() {
		return cookieId;
	}

	public int getCookieNum() {
		return cookieNum;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public void setCookieNum(int cookieNum) {
		this.cookieNum = cookieNum;
	}

	public static Cookie getInstance() {
		return ck;
	}
}