package board1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class Board1DaoImpl implements Board1Dao {

	private DBConnect db;
	private Connection conn;

	public Board1DaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Board1 b) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "insert into c_board1 values(seq_c_board1.nextval,?,?,sysdate,TO_DATE(?,'yyyy-mm-dd hh24:mi'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getType());
			pstmt.setString(2, b.getCate());
			pstmt.setString(3, b.getStartTime());
			pstmt.setString(4, b.getStartPosi());
			pstmt.setString(5, b.getEndPosi());

			pstmt.setDouble(6, b.getStartX());
			pstmt.setDouble(7, b.getStartY());
			pstmt.setDouble(8, b.getEndX());
			pstmt.setDouble(9, b.getEndY());
			pstmt.setInt(10, b.getPrice());
			pstmt.setString(11, b.getContent());
			pstmt.setString(12, b.getTitle());
			pstmt.setInt(13, b.getSeat());
			pstmt.setInt(14, b.getMaxSeat());
			pstmt.setString(15, b.getWriter());
			pstmt.setString(16, b.getDriver());
			pstmt.setString(17, b.getPassenger1());
			pstmt.setString(18, b.getPassenger2());
			pstmt.setString(19, b.getPassenger3());
			pstmt.setString(20, b.getProfile());
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public boolean update(Board1 b) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "update c_board1 set type=?, cate=?, w_date=sysdate, start_time=TO_DATE(?,'yyyy-mm-dd hh24:mi:ss'), start_posi=?,"
				+ "end_posi=?,start_x=?,start_y=?,end_x=?,end_y=?,price=?,content=?,title=?,seat=?,maxseat=?,driver=?,passenger1=?,passenger2=?,passenger3=?,profile=? where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getType());
			pstmt.setString(2, b.getCate());
			pstmt.setString(3, b.getStartTime());
			pstmt.setString(4, b.getStartPosi());
			pstmt.setString(5, b.getEndPosi());

			pstmt.setDouble(6, b.getStartX());
			pstmt.setDouble(7, b.getStartY());
			pstmt.setDouble(8, b.getEndX());
			pstmt.setDouble(9, b.getEndY());

			pstmt.setInt(10, b.getPrice());
			pstmt.setString(11, b.getContent());
			pstmt.setString(12, b.getTitle());
			pstmt.setInt(13, b.getSeat());
			pstmt.setInt(14, b.getMaxSeat());
			pstmt.setString(15, b.getDriver());
			pstmt.setString(16, b.getPassenger1());
			pstmt.setString(17, b.getPassenger2());
			pstmt.setString(18, b.getPassenger3());
			pstmt.setString(19, b.getProfile());
			pstmt.setInt(20, b.getNum());
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public Board1 selectByNum(int num) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where num=?";
		PreparedStatement pstmt;
		ResultSet rs;
		Board1 b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b = new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return b;
	}

	@Override
	public ArrayList<Board1> selectByCate(String cate) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where cate=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByTime(String start_time) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where start_time=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start_time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByStartPosi(String start_posi) {
		// TODO Auto-generated method stub

		conn = db.getConnection();
		String sql = "select * from c_board1 where start_posi like '%" + start_posi + "%' order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByEndPosi(String end_posi) {
		// TODO Auto-generated method stub

		conn = db.getConnection();
		String sql = "select * from c_board1 where end_posi like '%" + end_posi + "%' order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByStartEnd(String start_posi, String end_posi) {
		// TODO Auto-generated method stub

		conn = db.getConnection();
		String sql = "select * from c_board1 where start_posi like '%" + start_posi + "%' and end_posi like '%"
				+ end_posi + "%' order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByPrice(int price) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where price=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByWriter(String writer) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where writer like ? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectAll() {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
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
		String sql = "delete c_board1 where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public ArrayList<Board1> selectByType(int type) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where type=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public boolean updatePassenger(Board1 b) {
		conn = db.getConnection();
		String sql = "update c_board1 set passenger1=?,passenger2=?,passenger3=?, seat=? where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getPassenger1());
			pstmt.setString(2, b.getPassenger2());
			pstmt.setString(3, b.getPassenger3());
			pstmt.setInt(4, b.getSeat());
			pstmt.setInt(5, b.getNum());
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public ArrayList<Board1> selectByDriver(String driver) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where driver =? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, driver);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Board1> selectByPassenger(String passenger) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "select * from c_board1 where passenger1 =? or passenger2=? or passenger3=? order by num desc";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<Board1> list = new ArrayList<Board1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passenger);
			pstmt.setString(2, passenger);
			pstmt.setString(3, passenger);
			rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getInt(15),
						rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public boolean updateDriver(Board1 b) {
		conn = db.getConnection();
		String sql = "update c_board1 set driver=?, seat=? where num=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getDriver());
			pstmt.setInt(2, b.getSeat());
			pstmt.setInt(3, b.getNum());
			if (0 < pstmt.executeUpdate())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}
}