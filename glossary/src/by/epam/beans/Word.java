package by.epam.beans;

public class Word implements Comparable<Word>{
	private String word;

	public Word() {
		super();
	}

	@Override
	public String toString() {
		return word;
	}

	public Word(String word) {
		super();
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Character getFirstChar() {
		return word.charAt(0);
	}

	public void trasformWord(Character x) {
		StringBuilder sb = new StringBuilder();
		sb.append(x);
		for (Character c : word.toCharArray()) {
			if (!c.equals(x)) {
				// System.out.println(c);
				sb.append(c);
			}
		}
		word = sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public int compareTo(Word w) {
		// TODO Auto-generated method stub
		return getWord().compareTo(w.getWord());
	}


}
