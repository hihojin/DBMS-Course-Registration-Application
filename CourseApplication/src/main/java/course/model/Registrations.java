package course.model;

public class Registrations {
	protected int registrationId;
	protected int userId;
	protected int courseId;
	protected Status status;
	protected String courseName;
	protected String courseUrl;
	
	public enum Status {
		completed("COMPLETED"), inProgress("IN_PROGRESS"), dropped("DROPPED");
		private final String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public Registrations(int registrationId, int userId, int courseId, Status status) {
		this.registrationId = registrationId;
		this.userId = userId;
		this.courseId = courseId;
		this.status = status;
	}

	public Registrations(int userId, int courseId, Status status) {
		this.userId = userId;
		this.courseId = courseId;
		this.status = status;
	}

	public Registrations() {
	}

	public Registrations(int registrationId, int userId, int courseId, Status status, String courseName,
			String courseUrl) {
		super();
		this.registrationId = registrationId;
		this.userId = userId;
		this.courseId = courseId;
		this.status = status;
		this.courseName = courseName;
		this.courseUrl = courseUrl;
	}

	public Registrations(int userId, int courseId, Status status, String courseName, String courseUrl) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.status = status;
		this.courseName = courseName;
		this.courseUrl = courseUrl;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
}
