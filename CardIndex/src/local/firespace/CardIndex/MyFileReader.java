package local.firespace.CardIndex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MyFileReader {
	private BufferedReader in;
	private StringTokenizer st;
	private static final String FILENAME = "/sdcard/Download/books.txt";

	public MyFileReader() throws IOException {
		in = new BufferedReader(new FileReader(FILENAME));
	}

	public void close() throws IOException {
		in.close();
	}

	public Book nextBook() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine(), ",");
			if (!st.hasMoreTokens()) return null;
		}

		String id = st.nextToken(); id = id.substring(1, id.length()-1);
		String name = st.nextToken(); name = name.substring(1, name.length()-1);
		String author = st.nextToken(); author = author.substring(1, author.length()-1);
		String language = st.nextToken(); language = language.substring(1, language.length()-1);
		String count = st.nextToken(); count = count.substring(1, count.length()-1);
		String cost = st.nextToken(); cost = cost.substring(1, cost.length()-1);
		String url = st.nextToken(); url = url.substring(1, url.length()-1);

		return new Book(Integer.parseInt(id), name, author, language, Integer.parseInt(count), Integer.parseInt(cost), url);
	}
}