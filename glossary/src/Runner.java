

import java.util.Set;

import by.epam.beans.Param;
import by.epam.beans.Parser;
import by.epam.requestdispatcher.RequestDispatcher;
import by.epam.utils.FileUtil;

public class Runner {

	private static final String PATH = "src/text.txt";
	private static final String PATH_OUT = "src/out.txt";
	public static void main(String[] args) {
		RequestDispatcher rd = new RequestDispatcher(new Parser().parseText(FileUtil.readFile(PATH)).getSortedUniqueWordList());
		Set<Param> set = rd.start();
		FileUtil.out(set, PATH_OUT);
		System.out.println(set);
		
		
	}
}
