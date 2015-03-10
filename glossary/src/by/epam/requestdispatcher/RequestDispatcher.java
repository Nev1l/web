package by.epam.requestdispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import by.epam.beans.Param;
import by.epam.beans.Parser;
import by.epam.beans.Request;
import by.epam.beans.Response;
import by.epam.beans.Word;

public class RequestDispatcher {
	public final static String url = "https://translate.google.by/translate_a/single?client=t&sl=en&tl=ru&hl=ru&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&prev=bh&ssel=0&tsel=0&tk=518414|163684&q=";
	private List<Request> requests;
	private ExecutorService executor = Executors.newFixedThreadPool(100);

	public RequestDispatcher(Set<Word> wordList) {
		super();
		init(wordList);
	}

	private void init(Set<Word> wordList) {
		this.requests = new ArrayList<>(wordList.size());
		for (Word word : wordList) {
			requests.add(new Request(url, word.getWord().toLowerCase()));
		}
	}

	public Set<Param> start() {
		Set<Param> set = new TreeSet<>();
		Future<Response> response;
		try {
			Iterator<Request> it = requests.iterator();
			while (it.hasNext()) {
				Request req = it.next();
				response = executor.submit(req);
				try {
					String body = Parser.getWordTranlate(
							inputStreamToString(response.get().getBody())
									.toString().substring(4)).toLowerCase();
					if (!body.isEmpty()) {
						set.add(new Param(req.getWord(), body));
					}
				} catch (InterruptedException | ExecutionException
						| IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			executor.shutdown();
			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	private StringBuilder inputStreamToString(InputStream is)
			throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}
		return total;
	}
}
