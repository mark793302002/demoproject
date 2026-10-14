package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

	// 請依實際資料庫名稱 / 資料表名稱調整
	private static final String URL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Taipei&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	private static final String INSERT_SQL = "INSERT INTO classicmodels.SUPPLIERS (SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE classicmodels.SUPPLIERS SET SUP_NAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ? WHERE SUP_ID = ?";
	private static final String DELETE_SQL = "DELETE FROM classicmodels.SUPPLIERS WHERE SUP_ID = ?";
	private static final String FIND_BY_ID_SQL = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM classicmodels.SUPPLIERS WHERE SUP_ID = ?";
	private static final String FIND_ALL_SQL = "SELECT SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP FROM classicmodels.SUPPLIERS";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	@Override
	public void insert(Supplier supplier) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, supplier.getSupId());
			ps.setString(2, supplier.getSupName());
			ps.setString(3, supplier.getStreet());
			ps.setString(4, supplier.getCity());
			ps.setString(5, supplier.getState());
			ps.setString(6, supplier.getZip());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("新增 Supplier 失敗", e);
		}
	}

	@Override
	public void update(Supplier supplier) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, supplier.getSupName());
			ps.setString(2, supplier.getStreet());
			ps.setString(3, supplier.getCity());
			ps.setString(4, supplier.getState());
			ps.setString(5, supplier.getZip());
			ps.setInt(6, supplier.getSupId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("修改 Supplier 失敗", e);
		}
	}

	@Override
	public void delete(int supId) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, supId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("刪除 Supplier 失敗", e);
		}
	}

	@Override
	public Supplier findById(int supId) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_SQL)) {
			ps.setInt(1, supId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRow(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("查詢 Supplier 失敗", e);
		}
		return null;
	}

	@Override
	public List<Supplier> findAll() {
		List<Supplier> list = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ALL_SQL);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException("查詢全部 Supplier 失敗", e);
		}
		return list;
	}

	private Supplier mapRow(ResultSet rs) throws SQLException {
		return new Supplier(rs.getInt("SUP_ID"), rs.getString("SUP_NAME"), rs.getString("STREET"),
				rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"));
	}

	@Override
	public Supplier findById(String sid) {
		return findById(Integer.parseInt(sid.trim()));
	}
}
