package course.model;

public class Courses {

	protected int courseId;
	protected String courseName;
	protected String courseUrl;
	protected String courseDescription;
	protected CertificateType courseCertificateType;
	protected CourseDifficulty courseDifficulty;
	protected String institutionName;
	protected CourseSubject courseSubject;
	protected String regionCode;
	protected int professorsId;
	protected int taId;
	protected double rating;

	public enum CertificateType {
		masterLevel("MASTER LEVEL"), bachelorLevel("BACHELOR LEVEL"), highSchoolLevel(
				"HIGH SCHOOL LEVEL"), phdLevel("PHD LEVEL");
		private final String name;

		private CertificateType(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum CourseDifficulty {
		easy("EASY"), medium("MEDIUM"), hard("HARD"), SUPER_HARD("SUPER HARD");
		private final String name;

		private CourseDifficulty(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum CourseSubject {
		DATA_SCIENCE, BUSINESS, COMPUTER_SCIENCE, INFORMATION_TECHNOLOGY,
		LANGUAGE_LEARNING, HEALTH, PERSONAL_DEVELOPMENT, PHYSICAL_SCIENCE_AND_ENGINEERING,
		SOCIAL_SCIENCES, ARTS_AND_HUMANITIES, MATH_AND_LOGIC
	}

	// with id
	public Courses(int courseId, String courseName, String courseUrl, String courseDescription,
			CertificateType courseCertificateType, CourseDifficulty courseDifficulty,
			CourseSubject courseSubject,
			String institutionName, String regionCode, int professorsId,
			int taId) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseUrl = courseUrl;
		this.courseDescription = courseDescription;
		this.courseCertificateType = courseCertificateType;
		this.courseDifficulty = courseDifficulty;
		this.courseSubject = courseSubject;
		this.institutionName = institutionName;
		this.regionCode = regionCode;
		this.professorsId = professorsId;
		this.taId = taId;
	}

	// without id
	public Courses(String courseName, String courseUrl, String courseDescription,
			CertificateType courseCertificateType, CourseDifficulty courseDifficulty,
			CourseSubject courseSubject,
			String institutionName, String regionCode, int professorsId,
			int taId) {
		this.courseName = courseName;
		this.courseUrl = courseUrl;
		this.courseDescription = courseDescription;
		this.courseCertificateType = courseCertificateType;
		this.courseDifficulty = courseDifficulty;
		this.courseSubject = courseSubject;
		this.institutionName = institutionName;
		this.regionCode = regionCode;
		this.professorsId = professorsId;
		this.taId = taId;
	}

	public Courses(String courseName, String courseUrl, String courseDescription, CertificateType courseCertificateType,
			CourseDifficulty courseDifficulty, String institutionName, CourseSubject courseSubject, String regionCode,
			int professorsId, int taId, double rating) {
		this.courseName = courseName;
		this.courseUrl = courseUrl;
		this.courseDescription = courseDescription;
		this.courseCertificateType = courseCertificateType;
		this.courseDifficulty = courseDifficulty;
		this.institutionName = institutionName;
		this.courseSubject = courseSubject;
		this.regionCode = regionCode;
		this.professorsId = professorsId;
		this.taId = taId;
		this.rating = rating;
	}
	
	public Courses(int courseId, String courseName, String courseUrl, String courseDescription,
			CertificateType courseCertificateType, CourseDifficulty courseDifficulty,
			CourseSubject courseSubject,
			String institutionName, String regionCode, int professorsId,
			int taId, double rating) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseUrl = courseUrl;
		this.courseDescription = courseDescription;
		this.courseCertificateType = courseCertificateType;
		this.courseDifficulty = courseDifficulty;
		this.courseSubject = courseSubject;
		this.institutionName = institutionName;
		this.regionCode = regionCode;
		this.professorsId = professorsId;
		this.taId = taId;
		this.rating = rating;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	// constructor without any input
	public Courses() {
	}

	public Courses(int courseId2) {
		this.courseId = courseId2;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseUrl() {
		return courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public CertificateType getCourseCertificateType() {
		return courseCertificateType;
	}

	public void setCourseCertificateType(CertificateType courseCertificateType) {
		this.courseCertificateType = courseCertificateType;
	}

	public CourseDifficulty getCourseDifficulty() {
		return courseDifficulty;
	}

	public void setCourseDifficulty(CourseDifficulty courseDifficulty) {
		this.courseDifficulty = courseDifficulty;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public CourseSubject getCourseSubject() {
		return courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	public int getProfessorsId() {
		return professorsId;
	}

	public void setProfessorsId(int professorsId) {
		this.professorsId = professorsId;
	}

	public int getTaId() {
		return taId;
	}

	public void setTaId(int taId) {
		this.taId = taId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


}