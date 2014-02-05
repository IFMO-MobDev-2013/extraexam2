package local.firespace.CardIndex;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BookInformationActivity extends Activity {

	TextView name, author, lang, count, cost, url;
	int id;
	BookDBAdapter database;
	Book book;

	private void setInformation() {
		name.setText(book.getName());
		author.setText(book.getAuthor());
		lang.setText(book.getLang());
		count.setText(Integer.toString(book.getCount()));
		cost.setText(Integer.toString(book.getCost()));
		url.setText(book.getUrl());
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_information_layout);
		name = (TextView) findViewById(R.id.book_name);
		author = (TextView) findViewById(R.id.book_author);
		lang = (TextView) findViewById(R.id.book_language);
		count = (TextView) findViewById(R.id.book_count);
		cost = (TextView) findViewById(R.id.book_cost);
		url = (TextView) findViewById(R.id.book_url);
		id = getIntent().getIntExtra(BookDBAdapter.ID, 0);
		database = new BookDBAdapter(this);
		book = database.getBookByID(id);
		setInformation();
	}
}