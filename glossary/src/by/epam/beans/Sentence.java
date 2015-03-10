package by.epam.beans;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	public List<Word> wordList = new ArrayList<Word>();

	// create for each sentence symbol end of line
	public Sentence() {
		super();
	}

	Sentence(List<Word> wordList) {
		super();
		this.wordList = wordList;
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Word word : wordList) {
			sb.append(word.toString());
		}
		return sb.toString();
	}

	public boolean isQuestionSentence() {
		String s = this.toString();
		int l = s.length() - 1;
		return (l > 0 && s.charAt(l) == '?');
	}

	public void transformSentence() {
		for (Word w : getWordList()) {
			w.trasformWord(w.getFirstChar());
		}
	}

	public boolean hasWord(Word w) {
		for (Word word : getWordList()) {
			if (word.getWord().equals(w.getWord())) {
				return true;
			}
		}
		return false;
	}
}