package by.epam.requestdispatcher;

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
			requests.add(new Request(url, word.getWord()));
		}
	}

	public Set<Param> start() {
		List<Future<Response>> responses = new ArrayList<>(requests.size());
		Set<Param> set = new TreeSet<>();
		try {
			Iterator<Request> it = requests.iterator();
			while (it.hasNext()) {
				responses.add(executor.submit(it.next()));
			}
			for (int i = 0; i < responses.size(); i++) {
				try {
					set.add(new Param(requests.get(i).getWord(), responses
							.get(i).get().getWord()));
				} catch (InterruptedException | ExecutionException e) {
					//e.printStackTrace();
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
}
