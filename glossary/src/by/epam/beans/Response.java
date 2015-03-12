package by.epam.beans;


public class Response {
	private String word;

	public Response(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String getBody(boolean toLowerCase){
		return toLowerCase ? getWord() : getWord().toLowerCase();
	}
}