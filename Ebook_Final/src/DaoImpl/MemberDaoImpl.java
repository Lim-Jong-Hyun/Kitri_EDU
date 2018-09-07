package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataType.Member;
import DesignPattern.Cookie;
import DesignPattern.DBConnect;
import Interface.MemberDao;

public class MemberDaoImpl implements MemberDao {
	private DBConnect db;
	private Cookie ck;

	public MemberDaoImpl() {
		db = DBConnect.getInstance();
		ck = Cookie.getInstance();
	}

	@Override
	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		ResultSet rs;
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = db.getConnection();

		String sql = "select * from member";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public Member selectByMNum(Member m) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement pstmt;
		Member m2 = null;
		try {
			sql = "select * from member where m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getM_num());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m2 = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
			return m2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public Member selectByMID(String id) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Member m = null;
		try {

			sql = "select * from member where m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
			return m;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public Member selectByMNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement pstmt;
		Member m = null;
		try {
			sql = "select * from member where m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
			return m;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public Member selectByMId(Member m) {
		// TODO Auto-generated method stub
		ResultSet rs;
		Member m1 = null;
		Connection conn = db.getConnection();

		String sql = "select * from member where m_id=?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getM_id());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				m1 = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disConn();
		return m1;
	}

	@Override
	public ArrayList<Member> searchByMName(String name) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Member m = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			sql = "select * from member where m_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				list.add(m);
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
	public ArrayList<Member> searchByMDate(String String) {
		// TODO Auto-generated method stub

		/* 몇월 파라미터로 받아서 그 월에 가입한 사람들 목록을 보여줌 */
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Member m = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			sql = "select * from member where TO_CHAR(m_birth,'mm')=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				list.add(m);
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
	public ArrayList<Member> searchByMType(boolean b) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Member m = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			sql = "select * from member where m_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, b);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				list.add(m);
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
	public boolean insert(Member m) {
		// TODO Auto-generated method stub

		Connection conn = db.getConnection();

		String sql = "insert into member values(seq_member.nextval,'0',?,?,?,?,sysdate)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_name());
			pstmt.setString(4, m.getM_email());

			if (0 < pstmt.executeUpdate()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}

	@Override
	public boolean delete(Member m) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();

		String sql = "delete from member where m_id =?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getM_id());

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();

		}
		return false;
	}
	

	@Override
	public boolean delete(int m_num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();

		String sql = "delete from member where m_num = ?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, m_num);

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();

		}
		return false;
	}

	@Override
	public boolean delete(String m_id) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();

		String sql = "delete from member where m_id =?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m_id);

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();

		}
		return false;
	}

	@Override
	public boolean update(Member m) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "update member set m_pw=?, m_email = ? where m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getM_pw());
			pstmt.setString(2, m.getM_email());
			pstmt.setInt(3, ck.getCookieNum());
			
			if(0 < pstmt.executeUpdate()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;

	}

	@Override
	public Member selectMId(String m_id) {
		// TODO Auto-generated method stub
		ResultSet rs;
		Member m = null;
		Connection conn = db.getConnection();

		String sql = "select * from member where m_id=?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disConn();
		return m;
	}

	@Override
	public ArrayList<Member> searchAll() {
		// TODO Auto-generated method stub
		ResultSet rs;
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = db.getConnection();

		String sql = "select * from member";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Member> searchByMId(String m_id) {
		// TODO Auto-generated method stub
		ResultSet rs;
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = db.getConnection();

		String sql = "select * from member where m_id = ?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<Member> searchByMNum(int m_num) {
		// TODO Auto-generated method stub
		ResultSet rs;
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = db.getConnection();

		String sql = "select * from member where m_num = ?";
		PreparedStatement pstmt;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}
}
