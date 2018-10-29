package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class MemberDaoImpl implements MemberDao {
	
	private DBConnect db;
	private Connection conn;
	
	public MemberDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Member m) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "insert into c_member values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPw());
			ps.setString(3, m.getName());
			ps.setString(4, m.getEmail());
			ps.setString(5, m.getTel());
			ps.setString(6, m.getProfile());
			ps.setInt(7, m.getType());
			ps.setInt(8, m.getSex());
			ps.setInt(9, m.getIsSmoke());
			if(0 < ps.executeUpdate()) {
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
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "delete from c_member where id like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			if(0 < ps.executeUpdate()) {
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
	public boolean update(Member m) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "update c_member set pw=?, email=?, tel=?, profile=?, issmoke=? where id like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getPw());
			ps.setString(2, m.getEmail());
			ps.setString(3, m.getTel());
			ps.setString(4, m.getProfile());
			ps.setInt(5, m.getIsSmoke());
			ps.setString(6, m.getId());
			if(0 < ps.executeUpdate()) {
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
	public Member selectById(String id) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member where id like ?";
		Member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				m = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return m;
	}

	@Override
	public ArrayList<Member> selectByName(String name) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member where name like ?";
		ArrayList<Member> arr = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return arr;
	}

	@Override
	public ArrayList<Member> selectByIsSmoke(int isSmoke) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member where issmoke=?";
		ArrayList<Member> arr = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, isSmoke);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return arr;
	}

	@Override
	public ArrayList<Member> selectByType(int type) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member where type=?";
		ArrayList<Member> arr = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return arr;
	}

	@Override
	public ArrayList<Member> selectBySex(int sex) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member where sex=?";
		ArrayList<Member> arr = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sex);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return arr;
	}

	@Override
	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		ResultSet rs;
		String sql = "select * from c_member";
		ArrayList<Member> arr = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return arr;
	}
}