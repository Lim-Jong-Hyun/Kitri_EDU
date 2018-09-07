package ServiceImpl;

import java.util.ArrayList;

import DataType.Review;
import Interface.ReviewDao;
import Interface.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	private ReviewDao dao;

	public ReviewServiceImpl(ReviewDao dao) {
		this.dao = dao;
	}

	@Override
	public ArrayList<Review> showAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public boolean getReviewState(int p_num, int m_num) {
		// TODO Auto-generated method stub
		return dao.selectByWriteReview(p_num, m_num);
	}

	@Override
	public boolean insertReview(Review r) {
		// TODO Auto-generated method stub
		return dao.insert(r);
	}

	@Override
	public Review selectByRNum(int rnum) {
		// TODO Auto-generated method stub
		return dao.selectByRNum(rnum);
	}

	@Override
	public ArrayList<Review> selectByPNum(int pnum) {
		// TODO Auto-generated method stub
		return dao.selectByPNum(pnum);
	}

	@Override
	public ArrayList<Review> selectByMNum(int m_num) {
		// TODO Auto-generated method stub
		return dao.searchByMNum(m_num);
	}

	@Override
	public boolean deleteReview(Review r) {
		// TODO Auto-generated method stub
		return dao.delete(r);
	}
}
