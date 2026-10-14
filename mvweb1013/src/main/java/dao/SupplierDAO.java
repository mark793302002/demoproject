package dao;

import java.util.List;

public interface SupplierDAO {
	
	void insert(Supplier supplier);
	void update(Supplier supplier);
	void delete(int supId);
	Supplier findById(String sid);
	List<Supplier> findAll();
	Supplier findById(int supid);
	
}
