package Interface;

import java.util.ArrayList;

import DataType.Review;

public interface ReviewService {
	public boolean getReviewState(int p_num, int m_num);

	public boolean insertReview(Review r);

	public ArrayList<Review> showAll();
	
	public Review selectByRNum(int rnum);
	
	public ArrayList<Review> selectByPNum(int pnum);

	public ArrayList<Review> selectByMNum(int m_num);

	public boolean deleteReview(Review r);
}
