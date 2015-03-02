package by.epam.beans;

public class MusicArtist {
	private String name;
	private String image;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MusicArtist() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MusicArist [name=" + name + ", image=" + image
				+ ", description=" + description + "]";
	}

}
