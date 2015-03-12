package by.epam.template;

import by.epam.beans.Word;

public class DefaultCase implements Case{

	@Override
	public Word getWord(Word word) {
		return word;
	}

}
