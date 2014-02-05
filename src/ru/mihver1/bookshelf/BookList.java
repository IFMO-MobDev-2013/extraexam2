package ru.mihver1.bookshelf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import ru.mihver1.bookshelf.db.BookItem;
import ru.mihver1.bookshelf.db.DBStorageBooks;

import java.util.ArrayList;

public class BookList extends Activity {
    /**
     * Called when the activity is first created.
     */

    DBStorageBooks storage;

    public void search() {
        EditText et = (EditText) findViewById(R.id.editText);
        String s = String.valueOf(et.getText());
        ArrayList<BookItem> list = storage.getBookList(s);
        ListView lv = (ListView)findViewById(R.id.BookList);

        if(list != null) {
            RowAdapter rowAdapter = new RowAdapter(this, list);
            lv.setAdapter(rowAdapter);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        storage = new DBStorageBooks(this);

        ArrayList<BookItem> list = storage.getBookList(null);
        if(list == null) {
            String[] books = {
                "\"17562834\";\"Java. Руководство для начинающих\";\"Герберт Шилдт\";\"Русский\";\"624\";\"558\";\"http://www.ozon.ru/context/detail/id/17562834/\"",
                "\"23529814\";\"Структуры данных и алгоритмы в Java.\";\"Роберт Лафоре\";\"Русский\";\"704\";\"728\";\"http://www.ozon.ru/context/detail/id/23529814/\"",
                "\"7328622\";\"Java 7\";\"Ильдар Хабибуллин\";\"Русский\";\"768\";\"532\";\"http://www.ozon.ru/context/detail/id/7328622/\"",
                "\"7449696\";\"Web-сервисы Java\";\"Ильдар Хабибуллин\";\"Русский\";\"560\";\"401\";\"http://www.ozon.ru/context/detail/id/7449696/\"",
                "\"18567147\";\"Веселые научные опыты для детей и взрослых. Химия\";\"Сергей Болушевский\";\"Русский\";\"72\";\"191\";\"http://www.ozon.ru/context/detail/id/18567147/\"",
                "\"7251677\";\"Решение задач по химии. 8-11 класс\";\"Иван Хомченко\";\"Русский\";\"256\";\"145\";\"http://www.ozon.ru/context/detail/id/7251677/\"",
                "\"1391594716\";\"Java Programming Guide\";\"GradeStack Education\";\"Английский\";\"510\";\"0\";\"http://www.amazon.com/GradeStack-Education-Java-Programming-Guide/dp/B00F6NPLRI/ref=sr_1_3?ie=UTF8&qid=1391594716&sr=8-3&keywords=java\""
            };

            for(String book: books) {
                storage.addItem(book);
            }

            list = storage.getBookList(null);
        }

        ListView lv = (ListView)findViewById(R.id.BookList);
        RowAdapter rowAdapter = new RowAdapter(this, list);
        lv.setAdapter(rowAdapter);

        Button btnSearch = (Button) findViewById(R.id.button);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

    }
}
