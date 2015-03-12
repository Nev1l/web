package by.epam.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import by.epam.template.Case;
import by.epam.template.DefaultCase;
import by.epam.template.LowerCase;

public class Text {
	private List<Sentence> sentenceList = new ArrayList<Sentence>();

	public Text(List<Sentence> sentenceList) {
		super();
		this.sentenceList = sentenceList;
	}

	public List<Sentence> getSentenceList() {
		return sentenceList;
	}

	public void setSentenceList(List<Sentence> sentenceList) {
		this.sentenceList = sentenceList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Sentence s : sentenceList) {
			sb.append(s.toString());
		}
		return sb.toString();
	}

	public void outputWords(boolean toLowerCase) {
		for (Word w : getWordList(toLowerCase)) {
			System.out.println(w);
		}
	}

	public List<Word> getWordList(boolean toLowerCase) {
		Case c = toLowerCase ? new LowerCase() : new DefaultCase();
		List<Word> wordList = new ArrayList<>();
		for (Sentence sentence : getSentenceList()) {
			for (Word w : sentence.getWordList()) {
				wordList.add(c.getWord(w));
			}
		}
		return wordList;
	}

	public Set<Word> getSortedUniqueWordList(List<Word> wordList) {
		Set<Word> sortedUniqueList = new TreeSet<Word>();
		for (Word w : wordList) {
			sortedUniqueList.add(w);
		}
		return sortedUniqueList;
	}

}
