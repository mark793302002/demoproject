package dao;
//Entity Class
public class Supplier {
	int SupId;
	String SupName;
	String	Street;
	String	City;
	String State;
	String Zip;
	public Supplier() {}
	public Supplier(int supId, String supName, String street, String city, String state, String zip) {
		super();
		SupId = supId;
		SupName = supName;
		Street = street;
		City = city;
		State = state;
		Zip = zip;
	}
	public int getSupId() {
		return SupId;
	}
	public void setSupId(int supId) {
		SupId = supId;
	}
	public String getSupName() {
		return SupName;
	}
	public void setSupName(String supName) {
		SupName = supName;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	
	
}
