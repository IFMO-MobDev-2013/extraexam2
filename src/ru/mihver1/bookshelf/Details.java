package ru.mihver1.bookshelf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by mihver1 on 05.02.14.
 */
public class Details extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.details);

        Bundle bundle = getIntent().getExtras();

        String csv = bundle.getString("csvInfo");
        String[] input = csv.split(";");

        TextView name = (TextView) findViewById(R.id.detaildName);
        name.setText(input[1]);
        TextView author = (TextView) findViewById(R.id.detailsAuthor);
        author.setText(input[2]);
        TextView price = (TextView) findViewById(R.id.detailsPrice);
        price.setText(input[5] + " RUR");
        TextView pages = (TextView) findViewById(R.id.detailsPages);
        pages.setText("Страниц: " + input[4]);
        TextView url = (TextView) findViewById(R.id.detailsURL);
        url.setText(input[6]);
        TextView ids = (TextView) findViewById(R.id.detailsID);
        ids.setText("ID "+ input[0]);
        TextView lang = (TextView) findViewById(R.id.detailsLang);
        lang.setText("Язык: " + input[3]);

        ListView lv = (ListView) findViewById(R.id.listView);

    }
}