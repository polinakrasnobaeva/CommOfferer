
public class Manager {
	
	private String name;
	private String email;
	private String phonenumber;
	
	public Manager(String name) {
		super();
		this.name = name;
	}
	
	public Manager(Manager m) {
		super();
		this.name = m.getName();
		this.email = m.getEmail();
		this.phonenumber = m.getPhonenumber();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String toString(){
		return this.name;
	}
}
