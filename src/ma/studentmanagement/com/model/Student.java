package ma.studentmanagement.com.model;

public class Student {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String studentId;
	protected String school;
	protected String studyOption;
	protected String registrationYear;

	public Student() {
	}

	public Student(Integer id, String firstName, String lastName, String studentId, String school, String studyOption,
			String registrationYear) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
		this.school = school;
		this.studyOption = studyOption;
		this.registrationYear = registrationYear;
	}

	public Student(String firstName, String lastName, String studentId, String school, String studyOption,
			String registrationYear) {
		this(null, firstName, lastName, studentId, school, studyOption, registrationYear);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudyOption() {
		return studyOption;
	}

	public void setStudyOption(String studyOption) {
		this.studyOption = studyOption;
	}

	public String getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}

}
