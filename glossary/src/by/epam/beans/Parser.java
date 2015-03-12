package by.epam.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private int minLength = 6;
	private int maxLength = Integer.MAX_VALUE;
	private String REGEX_SENTENCE = "([^\\.|!|\\?]*)([\\.|!|\\?]*)";
	private String REGEX_SIGN = "([\\p{InCyrillic}|\\w?-^\\d]{" + minLength
			+ "," + maxLength + "})";
	private static String REGEX_RU = "^([\\p{InCyrillic}]{4,})";
	// private static String REGEX_ENG = "([\\w^\\d]{3,})";
	public final static int GROUP_1 = 1;
	public final static int GROUP_2 = 2;
	public final static int GROUP_3 = 3;

	public Parser() {
		super();
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		if (minLength > 0 && minLength <= maxLength) {
			this.minLength = minLength;
		}
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		if (maxLength >= minLength && maxLength > 0) {
			this.maxLength = maxLength;
		}
	}

	private List<Sentence> parseToSentence(String text) {
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		Pattern pattern = Pattern.compile(REGEX_SENTENCE);
		Matcher matcher = pattern.matcher(text);
		String s = "";
		while (matcher.find()) {
			s = matcher.group();
			sentenceList.add(new Sentence(parseToWord(s)));
		}
		return sentenceList;
	}

	public static String getWordTranlate(String value) throws Exception {
		Pattern pattern = Pattern.compile(REGEX_RU);
		Matcher matcher = pattern.matcher(value);
		if (matcher.find()) {
			return matcher.group(GROUP_1);
		}
		throw new Exception("Method didn't catch a translation word.");
	}

	private List<Word> parseToWord(String sentence) {
		List<Word> wordList = new ArrayList<Word>();
		Pattern pattern = Pattern.compile(REGEX_SIGN);
		Matcher matcher = pattern.matcher(sentence);
		String word;
		while (matcher.find()) {
			word = matcher.group(GROUP_1);
			wordList.add(new Word(word));
		}
		return wordList;
	}

	public Text parseText(String text) {
		return new Text(parseToSentence(text));
	}
}
