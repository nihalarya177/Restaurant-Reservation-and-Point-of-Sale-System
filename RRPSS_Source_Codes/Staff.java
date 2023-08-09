public class Staff extends Person {
	
	private int staffID;
	private String jobTitle;

	public Staff(String name, String gender, int staffID, String jobTitle) {
		super(name, gender);
		this.staffID = staffID;
		this.jobTitle = jobTitle;
	}
	
	public int getEmployeeID() {
		return this.staffID;
	}
	
	public void setEmployeeID(int employee_id) {
		this.staffID = employee_id;
	}
	
	public String getjobTitle() {
		return this.jobTitle;
	}
	
	public void setjobTitle(String job_title) {
		this.jobTitle = job_title;
	}

}
