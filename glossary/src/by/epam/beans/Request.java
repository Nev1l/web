package by.epam.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class Request implements Callable<Response> {
	private String url;
	private String word;

	public Request(String url, String word) {
		super();
		this.word = word;
		this.url = url;
	}


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public Response call() throws Exception {
		URLConnection conn = null;
		try {
			conn = new URL(url+word).openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
			conn.setRequestProperty("content-type", "text/html; charset=UTF-8");
			conn.setRequestProperty("Content-Language", "ru");
			conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			return new Response(Parser.getWordTranlate(inputStreamToString(conn.getInputStream()).substring(4)));
		} catch (IOException e1) {
			//e1.printStackTrace();
			throw new Exception(e1);
		}
	}
	
	private String inputStreamToString(InputStream is) throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}
		return total.toString();
	}
}