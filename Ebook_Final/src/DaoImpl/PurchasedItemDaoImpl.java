package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataType.PurchasedItem;
import DesignPattern.DBConnect;
import Interface.PurchasedItemDao;

public class PurchasedItemDaoImpl implements PurchasedItemDao {
	private DBConnect db;

	public PurchasedItemDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(PurchasedItem pi) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "insert into purchased_item values(seq_order.nextval, ?, ?, '0', null, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pi.getm_num());
			pstmt.setInt(2, pi.getP_num());
			pstmt.setString(3, pi.getP_name());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}
	
/*	아래있는 delete 는 종현씨가 작성한 delete 인데 종현씨껄로 하는걸로... 종현씨는 o_num, 이건 p_num 혹시 쓰일지 모르니 남겨둡니다...
	@Override
	public boolean delete(PurchasedItem pi) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "delete from purchased_item where p_num = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pi.getP_num());
			
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
*/

	@Override
	public boolean delete(PurchasedItem pi) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql = "delete from purchased_item where o_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pi.getO_num());
			
			int s =pstmt.executeUpdate();
			if(0<s) {
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
	public boolean update(PurchasedItem pi) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql ="update Purchased_item set ispayed = 1, purchased_date = sysdate where o_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pi.getO_num());
			int s =pstmt.executeUpdate();
			if(0<s) {
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
	public ArrayList<PurchasedItem> selectAll() {
		// TODO Auto-generated method stub
		ArrayList <PurchasedItem> list = new ArrayList<PurchasedItem>();
		PurchasedItem pci = null;
		Connection conn = db.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement pstmt;
		try {
			sql = "select * from Purchased_Item";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pci = new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6));
				list.add(pci);
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
	public PurchasedItem selectByONum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		PurchasedItem pci=null;
		try {
			sql ="select * from  Purchased_Item where o_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			pci = new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return pci;
	}
	
	@Override
	public ArrayList<PurchasedItem> selectByMNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		try {
			sql ="select * from  Purchased_Item where m_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			list.add(new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	// 개인 유저의 구매한 목록가져오기
	@Override
	public ArrayList<PurchasedItem> selectByUserPurchased(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		try {
			sql ="select * from  Purchased_Item where m_num =? and ispayed = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			list.add(new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	// 개인 유저의 구매안된 목록가져오기
	@Override
	public ArrayList<PurchasedItem> selectByUserCart(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		try {
			sql ="select * from Purchased_Item where m_num =? and ispayed = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			list.add(new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6)));
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
	public ArrayList<PurchasedItem> selectByPNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		try {
			sql ="select * from  Purchased_Item where p_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			list.add(new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public ArrayList<PurchasedItem> selectByPPaid(boolean b) {
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		try {
			sql ="select * from  Purchased_Item where ispayed =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1,b);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			list.add(new PurchasedItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getBoolean(4),rs.getString(5), rs.getString(6)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return list;
	}

	@Override
	public boolean checkInsertCart(PurchasedItem pi) {
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			sql ="select * from Purchased_Item where m_num = ? and p_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pi.getm_num());
			pstmt.setInt(2, pi.getP_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return false;
	}
}
