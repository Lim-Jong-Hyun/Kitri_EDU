package DataType;

public class Review {
	// 리뷰번호 (pk, 시퀀스 자동배정)
	private int r_num;

	// 누가 썻는지 멤버넘버 fk
	private int m_num;

	// Item p_num fk
	private int p_num;

	// 별점
	private int r_score;

	// 리뷰내용
	private String r_contents;

	public Review() {
	}

	public Review(int r_num, int m_num, int p_num, int r_score, String r_contents) {
		this.r_num = r_num;
		this.m_num = m_num;
		this.p_num = p_num;
		this.r_score = r_score;
		this.r_contents = r_contents;
	}

	public String toSubString() {
		String str = String.valueOf(r_score);
		if (r_score == 1) {
			str = "★";
		} else if (r_score == 2) {
			str = "★★";
		} else if (r_score == 3) {
			str = "★★★";
		} else if (r_score == 4) {
			str = "★★★★";
		} else if (r_score == 5) {
			str = "★★★★★";
		}

		return "[" + r_num + " 번] " + " 책번호:" + p_num + " / 별점:" + str;

	}

	public String toSubString2() {
		String str = String.valueOf(r_score);
		if (r_score == 1) {
			str = "★";
		} else if (r_score == 2) {
			str = "★★";
		} else if (r_score == 3) {
			str = "★★★";
		} else if (r_score == 4) {
			str = "★★★★";
		} else if (r_score == 5) {
			str = "★★★★★";
		}

		return "책번호:" + p_num + " / 별점:" + str + " / 내용:" + r_contents;

	}

	public String toSubString3() {
		return "\n- 내용 -\n" + r_contents;
	}

	@Override
	public String toString() {
		return "Review [r_num=" + r_num + ", m_num=" + m_num + ", p_num=" + p_num + ", r_score=" + r_score
				+ ", r_contents=" + r_contents + "]";
	}

	public int getR_num() {
		return r_num;
	}

	public int getM_num() {
		return m_num;
	}

	public int getP_num() {
		return p_num;
	}

	public int getR_score() {
		return r_score;
	}

	public String getR_contents() {
		return r_contents;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public void setR_score(int r_score) {
		this.r_score = r_score;
	}

	public void setR_contents(String r_contents) {
		this.r_contents = r_contents;
	}
}
