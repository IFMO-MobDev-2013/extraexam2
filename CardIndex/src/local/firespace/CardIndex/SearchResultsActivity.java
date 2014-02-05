package local.firespace.CardIndex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchResultsActivity extends Activity {

	Book[] books;
	TextView tvRequest;
	ListView results;
	String request;

	private void makeList() {
		if (books == null) books = new Book[0];
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>(books.length);
		Map<String, String> m;
		for (Book book : books) {
			m = new HashMap<String, String>();
			m.put(BookDBAdapter.NAME, book.getName());
			m.put(BookDBAdapter.AUTHOR, book.getAuthor());
			m.put(BookDBAdapter.COST, Integer.toString(book.getCost()));
			data.add(m);
		}

		String[] from = {BookDBAdapter.NAME, BookDBAdapter.AUTHOR, BookDBAdapter.COST};
		int[] to = {R.id.name, R.id.author, R.id.cost};
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listitem, from, to);
		results.setAdapter(adapter);
		results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SearchResultsActivity.this, BookInformationActivity.class);
				intent.putExtra(BookDBAdapter.ID, books[position].getId());
				startActivity(intent);
			}
		});
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_layout);
		request = getIntent().getStringExtra(BookDBAdapter.NAME);
		BookDBAdapter database = new BookDBAdapter(this);
		books = database.getBooksByName(request);
		tvRequest = (TextView) findViewById(R.id.request);
		results = (ListView) findViewById(R.id.results);
		tvRequest.setText(request);
		makeList();
	}
}