package by.epam.beans;

public class Genre {
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Genre) obj).getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
