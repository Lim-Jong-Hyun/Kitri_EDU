package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class CarDaoImpl implements CarDao {

	private DBConnect db;
	private Connection conn;

	public CarDaoImpl() {
		db = DBConnect.getInstance();
	}

	@Override
	public boolean insert(Car c) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "";
		PreparedStatement pstmt;
		try {
			sql = "insert into c_car values (?,?,?,?,to_date(?,'yyyy'),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getOwner());
			pstmt.setString(2, c.getLicense());
			pstmt.setString(3, c.getNumberPlate());
			pstmt.setInt(4, c.getMileage());
			pstmt.setString(5, c.getAge());
			pstmt.setString(6, c.getCarName());
			pstmt.setInt(7, c.getCarSize());
			if(0<pstmt.executeUpdate())
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
	public boolean delete(String owner) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "delete c_car where owner=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, owner);
			if(0<pstmt.executeUpdate())
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
	public boolean update(Car c) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		String sql = "update c_car set license=?, number_plate=?, mileage=?, age=to_date(?,'yyyy'), ?, car_size=?  where owner=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getOwner());
			pstmt.setString(2, c.getLicense());
			pstmt.setString(3, c.getNumberPlate());
			pstmt.setInt(4, c.getMileage());
			pstmt.setString(5, c.getAge());
			pstmt.setString(6, c.getCarName());
			pstmt.setInt(7, c.getCarSize());
			if(0<pstmt.executeUpdate())
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
	public Car select(String owner) {
		// TODO Auto-generated method stub
		ResultSet rs;
		conn = db.getConnection();
		String sql = "select owner, license, number_plate, mileage, to_char(age,'yyyy'), car_name, car_size from c_car where owner=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, owner);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

	@Override
	public ArrayList<Car> selectAll() {
		// TODO Auto-generated method stub
		ResultSet rs;
		ArrayList<Car> list = new ArrayList<Car>();
		conn = db.getConnection();
		String sql = "select * from c_car ";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.disConn();
		}
		return null;
	}

}
