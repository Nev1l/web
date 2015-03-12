package by.epam.template;

import by.epam.beans.Word;

public class LowerCase implements Case{

	@Override
	public Word getWord(Word word) {
		word.setWord(word.getWord().toLowerCase());
		return word;
	}
}
