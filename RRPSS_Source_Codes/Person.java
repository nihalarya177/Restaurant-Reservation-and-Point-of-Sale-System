public class Person 
{
	private String name;
	private String gender;

	public Person(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(String Gender) {
		this.gender = Gender;
	}

}
