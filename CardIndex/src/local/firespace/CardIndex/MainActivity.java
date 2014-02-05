package local.firespace.CardIndex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	BookDBAdapter database;
	EditText bookName;

	private void readBooks() {
		try {
			MyFileReader reader = new MyFileReader();
			Book book = reader.nextBook();
			while (book != null) {
				database.insertBook(book);
				book = reader.nextBook();
			}
			reader.close();
		} catch (Exception e) {}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		bookName = (EditText) findViewById(R.id.editText);
		database = new BookDBAdapter(this);
		//database.clear();
		if (database.isEmpty()) {
			readBooks();
		}
	}

	public void enter(View view) {
		try {
			String name = bookName.getText().toString();
			Intent intent = new Intent(this, SearchResultsActivity.class);
			intent.putExtra(BookDBAdapter.NAME, name);
			startActivity(intent);
		} catch (Exception e) { //empty request not handle
		}
	}
}