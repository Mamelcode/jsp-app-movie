package data;

public class LikeDirector {

	String directorId;
	String id;
	String posterURL;
	String directorName;
	boolean like;

	public String getDirectorId() {
		return directorId;
	}

	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

}
