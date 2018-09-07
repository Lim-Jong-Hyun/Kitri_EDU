package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataType.Item;
import DesignPattern.DBConnect;
import Interface.ItemDao;

public class ItemDaoImpl implements ItemDao {
	private DBConnect db;

	public ItemDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql = "insert into ITEM values (seq_item.nextval,?,TO_DATE(?,'YYYY'),?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getP_name());
			pstmt.setString(2, i.getP_public());
			pstmt.setString(3, i.getP_writer());
			pstmt.setString(4, i.getP_genre());
			pstmt.setString(5, i.getP_publisher());
			pstmt.setString(6, i.getP_summary());
			pstmt.setString(7, i.getP_fileName());
			pstmt.setInt(8, i.getP_price());
			int s = pstmt.executeUpdate();
			if (0 < s) {
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
	public boolean delete(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql = "delete from item where p_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if (0 < pstmt.executeUpdate()) {
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
	public boolean update(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql = "update item set p_name=?,p_public=TO_DATE(?,'YYYY'),p_writer=?,p_genre=?,p_publisher=?,p_summary=?,p_price=? where p_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getP_name());
			pstmt.setString(2, i.getP_public());
			pstmt.setString(3, i.getP_writer());
			pstmt.setString(4, i.getP_genre());
			pstmt.setString(5, i.getP_publisher());
			pstmt.setString(6, i.getP_summary());
			pstmt.setInt(7, i.getP_price());
			pstmt.setInt(8, i.getP_num());

			int s = pstmt.executeUpdate();
			if (0 < s) {
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
	public Item selectByPNum(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from Item where p_num = ?";
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i.getP_num());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int p_num = rs.getInt(1);
				String p_name = rs.getString(2);
				String p_public = rs.getString(3);
				String p_writer = rs.getString(4);
				String p_genre = rs.getString(5);
				String p_publisher = rs.getString(6);
				String p_summary = rs.getString(7);
				String p_fileName = rs.getString(8);
				int p_price = rs.getInt(9);
				rs.close();
				return new Item(p_num, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName,
						p_price);
			}
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public Item selectByPNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		try {
			sql = "select * from ITEM where p_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return i;
	}

	@Override
	public ArrayList<Item> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			sql = "select * from item order by p_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(i);
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
	public ArrayList<Item> searchByPName(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from Item where p_name like '%' || ? || '%'";
		ArrayList<Item> arr = new ArrayList<Item>();
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.getResultSet();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getP_name());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_num = rs.getInt(1);
				String p_name = rs.getString(2);
				String p_public = rs.getString(3);
				String p_writer = rs.getString(4);
				String p_genre = rs.getString(5);
				String p_publisher = rs.getString(6);
				String p_summary = rs.getString(7);
				String p_fileName = rs.getString(8);
				int p_price = rs.getInt(9);
				arr.add(new Item(p_num, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName,
						p_price));
			}
			rs.close();
			return arr;
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Item> searchByPName(String name) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			sql = "select * from ITEM where p_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(i);
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
	public ArrayList<Item> searchByPWriter(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from Item where p_writer like '%' || ? || '%'";
		ArrayList<Item> arr = new ArrayList<Item>();
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.getResultSet();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getP_writer());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_num = rs.getInt(1);
				String p_name = rs.getString(2);
				String p_public = rs.getString(3);
				String p_writer = rs.getString(4);
				String p_genre = rs.getString(5);
				String p_publisher = rs.getString(6);
				String p_summary = rs.getString(7);
				String p_fileName = rs.getString(8);
				int p_price = rs.getInt(9);
				arr.add(new Item(p_num, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName,
						p_price));
			}
			rs.close();
			return arr;
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Item> searchByPWriter(String writer) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			sql = "select * from ITEM where p_writer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(i);
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
	public ArrayList<Item> searchByPGenre(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from Item where p_genre like ?";
		ArrayList<Item> arr = new ArrayList<Item>();
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.getResultSet();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getP_genre());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_num = rs.getInt(1);
				String p_name = rs.getString(2);
				String p_public = rs.getString(3);
				String p_writer = rs.getString(4);
				String p_genre = rs.getString(5);
				String p_publisher = rs.getString(6);
				String p_summary = rs.getString(7);
				String p_fileName = rs.getString(8);
				int p_price = rs.getInt(9);
				arr.add(new Item(p_num, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName,
						p_price));
			}
			rs.close();
			return arr;
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Item> searchByPGenre(String genre) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			sql = "select * from ITEM where p_genre = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genre);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(i);
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
	public ArrayList<Item> searchByPPrice(Item i) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "select * from Item where p_price = ?";
		ArrayList<Item> arr = new ArrayList<Item>();
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.getResultSet();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i.getP_price());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int p_num = rs.getInt(1);
				String p_name = rs.getString(2);
				String p_public = rs.getString(3);
				String p_writer = rs.getString(4);
				String p_genre = rs.getString(5);
				String p_publisher = rs.getString(6);
				String p_summary = rs.getString(7);
				String p_fileName = rs.getString(8);
				int p_price = rs.getInt(9);
				arr.add(new Item(p_num, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName,
						p_price));
			}
			rs.close();
			return arr;
			// pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Item> searchByPPrice(int price) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		ResultSet rs;
		Item i = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			sql = "select * from ITEM where p_price=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(i);
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
	public Item select(String p_name) {
		// TODO Auto-generated method stub
		ResultSet rs;
		Item g = null;

		Connection conn = db.getConnection();

		String sql = "select * from Purchased_Item where p_name like '%' || ? || '%'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String p_name1 = rs.getString(1);
				String p_public = rs.getString(2);
				String p_writer = rs.getString(3);
				String p_genre = rs.getString(4);
				String p_publisher = rs.getString(5);
				String p_summary = rs.getString(6);
				int p_price = rs.getInt(7);

				g = new Item(0, p_name1, p_public, p_writer, p_genre, p_publisher, p_summary, p_summary, p_price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disConn();
		return g;
	}

	@Override
	public boolean delete(String p_name) {
		// TODO Auto-generated method stub
		Connection conn = db.getConnection();

		String sql = "delete from Item  where p_name=? ";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disConn();
		return false;
	}
}
