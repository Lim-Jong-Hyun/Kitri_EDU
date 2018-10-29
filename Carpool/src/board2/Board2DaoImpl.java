package board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class Board2DaoImpl implements Board2Dao {

	private DBConnect db;
	private Connection conn;

	public Board2DaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Board2 b) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "insert into c_board2 values(seq_c_board2.nextval,?,sysdate,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getType());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getWriter());
			if(0<pstmt.executeUpdate())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public boolean update(Board2 b) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "update c_board2 set title=?, w_date=sysdate, content=? where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getNum());
			if(0<pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public Board2 selectByNum(int num) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board2 where num=?";
		PreparedStatement pstmt;
		ResultSet rs;
		Board2 b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b = new Board2(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return b;
	}

	@Override
	public ArrayList<Board2> selectByWriter(String writer) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board2 where writer=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board2> list = new ArrayList<Board2>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board2(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board2> selectAll() {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board2 order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board2> list = new ArrayList<Board2>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board2(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public boolean delete(int num) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "delete c_board2 where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(0<pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public ArrayList<Board2> selectByType(int type) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board2 where type=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board2> list = new ArrayList<Board2>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board2(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}
}