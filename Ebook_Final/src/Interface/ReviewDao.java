package Interface;

import java.util.ArrayList;

import DataType.Review;

public interface ReviewDao {
	public boolean insert(Review r);

	public boolean delete(Review r);

	public ArrayList<Review> selectAll();

	public boolean selectByWriteReview(int p_num, int m_num);

	public Review selectByRNum(int rnum);
	
	public ArrayList<Review> selectByPNum(int pnum);

	public ArrayList<Review> searchByMNum(int m_num);
}