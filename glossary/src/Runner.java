

import java.util.Set;

import by.epam.beans.Param;
import by.epam.beans.Parser;
import by.epam.beans.Text;
import by.epam.requestdispatcher.RequestDispatcher;
import by.epam.utils.FileUtil;

public class Runner {

	private static final String PATH = "src/text.txt";
	private static final String PATH_OUT = "src/out.txt";
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Parser parser = new Parser();
		Text text = parser.parseText(FileUtil.readFile(PATH));
		RequestDispatcher rd = new RequestDispatcher(text.getSortedUniqueWordList(text.getWordList(true)));
		Set<Param> result = rd.start();
		FileUtil.out(result, PATH_OUT);
		System.out.println("COMPLEATE="+result.size());
		System.out.println( System.currentTimeMillis()-start);
		//FileUtil.out(FileUtil.readAndSplitFile("src/outText.txt"),"src/referatWords.txt");
		//574=5845     8455    without threading 55298
		/*Set<String> set = new TreeSet<>();
		set.add("cup");
		set.add("cups");
		set.add("Cup");
		System.out.println(set);
		for (String string : set) {
			//set
		}*/
	}
}
