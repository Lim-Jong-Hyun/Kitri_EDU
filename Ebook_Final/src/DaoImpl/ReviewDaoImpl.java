package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataType.Review;
import DesignPattern.DBConnect;
import Interface.ReviewDao;

public class ReviewDaoImpl implements ReviewDao {
	private DBConnect db;

	public ReviewDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Review r) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "insert into review values (seq_review.nextval,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getM_num());
			pstmt.setInt(2, r.getP_num());
			pstmt.setInt(3, r.getR_score());
			pstmt.setString(4, r.getR_contents());

			// pstmt.executeQuery();
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public boolean delete(Review r) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "delete from review where r_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getR_num());

			// pstmt.executeQuery();
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public ArrayList<Review> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Review r = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			sql = "select * from review";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				r = new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				list.add(r);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public Review selectByRNum(int rnum) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Review r = null;
		try {
			sql = "select * from Review where r_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				r = new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return r;
	}

	@Override
	public ArrayList<Review> selectByPNum(int pnum) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Review r = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			sql = "select * from review where p_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				r = new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				list.add(r);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public boolean selectByWriteReview(int p_num, int m_num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from review where p_num = ? and m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			pstmt.setInt(2, m_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public ArrayList<Review> searchByMNum(int m_num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		ResultSet rs;
		ArrayList<Review> r = new ArrayList<Review>();
		String sql = "select * from review where m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				r.add(new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			return r;
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

}
