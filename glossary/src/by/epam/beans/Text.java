package by.epam.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

	public void outputWords() {
		for (Sentence sentence : getSentenceList()) {
			for (Word w : sentence.getWordList()) {
				System.out.println(w);
			}
		}
	}

	public Set<Word> getSortedUniqueWordList() {
		Set<Word> sortedUniqueList = new TreeSet<Word>();//LinkedHashSet
		for (Sentence sentence : getSentenceList()) {
			for (Word w : sentence.getWordList()) {
				sortedUniqueList.add(w);
			}
		}
		return sortedUniqueList;
	}

}
