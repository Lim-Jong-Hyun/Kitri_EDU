package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataType.Account;
import DesignPattern.DBConnect;
import Interface.AccountDao;

public class AccountDaoImpl implements AccountDao {
	private DBConnect db;

	public AccountDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Account a) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "insert into account values (?,?,?,?)";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getm_num());
			pstmt.setString(2, a.getA_name());
			pstmt.setString(3, a.getA_accountNum());
			pstmt.setInt(4, a.getA_balance());
			//pstmt.executeQuery();
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
	public void delete(int m_num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "delete from account where m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, m_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		
	}

	@Override
	public boolean delete(Account a) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "delete from account where m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.executeQuery();
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
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "update account set balance = balance + ? where m_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getA_balance());
			pstmt.setInt(2, a.getm_num());
			//pstmt.executeQuery();
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
	public Account selectByMNum(Account a) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from account where m_num = ?";
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getm_num());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int m_num = rs.getInt(1);
				String a_name = rs.getString(2);
				String a_accountNum = rs.getString(3);
				int a_balance = rs.getInt(4);
				return new Account(m_num, a_name, a_accountNum, a_balance);
			}
			rs.close();
			//pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public Account selectByMNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from account where m_num = ?";
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int m_num = rs.getInt(1);
				String a_name = rs.getString(2);
				String a_accountNum = rs.getString(3);
				int a_balance = rs.getInt(4);
				return new Account(m_num, a_name, a_accountNum, a_balance);
			}
			rs.close();
			//pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Account> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList <Account> list = new ArrayList<Account>();
		try {
			sql = "select * from Account";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int m_num = rs.getInt(1);
				String a_name = rs.getString(2);
				String a_accountNum = rs.getString(3);
				int a_balance = rs.getInt(4);
				list.add(new Account(m_num, a_name, a_accountNum, a_balance));
			}
			
			//pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}
}