package com.mobdev.books;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends Activity {
	EditText edt;
	Button btn;
	GridView grv;
	BooksDB helper;
	SQLiteDatabase db;
	ArrayList<String> all;
	ArrayList<String> searched;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt = (EditText)findViewById(R.id.edt);
		btn = (Button)findViewById(R.id.btn);
		grv = (GridView)findViewById(R.id.grv);
		helper = new BooksDB(this);
		db = helper.getWritableDatabase();
		all = new ArrayList<String>();
		searched = new ArrayList<String>();
		
		Cursor cursor = db.query(BooksDB.DATABASE_TABLE, new String[] { BooksDB.B_ID }, null, null,
				null, null, null);
		
		if (cursor.getCount()==0)
			addFromFile();
		else
			showAll();
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String str = edt.getText().toString();
				if (str.length() != 0)
					searchAndShow(str);
				else
					showAll();
			}
		});
			
	}
	
	private void addFromFile(){
		 try {
	            AssetManager assetManager = this.getAssets();
	            InputStreamReader istream = new InputStreamReader(assetManager.open("books.txt"));
	            BufferedReader in = new BufferedReader(istream);
	            
	            String str="";
	            while ((str = in.readLine()) != null) {
	            	 addToDBFromString(str);
	            }
	            in.close();
	        } catch (FileNotFoundException e) {
	                  
	        } catch (IOException e) {
	            
	        }       
		showAll();
	}
	
	private void showAll(){
		all = new ArrayList<String>();
		Cursor cursor = db.query(BooksDB.DATABASE_TABLE, new String[] { BooksDB.B_ID,
				BooksDB.B_NAME, BooksDB.B_AUTHOR, BooksDB.B_LANG,
				BooksDB.B_SIZE, BooksDB.B_PRICE, BooksDB.B_URL }, null, null,
				null, null, null);
		cursor.moveToFirst();
		do{
			String name = cursor.getString(cursor.getColumnIndex(BooksDB.B_NAME));
			String author = cursor.getString(cursor.getColumnIndex(BooksDB.B_AUTHOR));
			String price = cursor.getString(cursor.getColumnIndex(BooksDB.B_PRICE));
			String str = name + "\n" + author + "\n" + price + "ð";
			all.add(str);
		} while (cursor.moveToNext());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, all);
		grv.setAdapter(adapter);
		grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,InformationActivity.class);
				intent.putExtra(InformationActivity.BOOK, position);
				startActivity(intent);
			}
		});
	}
	
	private void searchAndShow(String str){
		searched = new ArrayList<String>();
		Cursor cursor = db.query(BooksDB.DATABASE_TABLE, new String[] { BooksDB.B_ID,
				BooksDB.B_NAME, BooksDB.B_AUTHOR, BooksDB.B_LANG,
				BooksDB.B_SIZE, BooksDB.B_PRICE, BooksDB.B_URL }, null, null,
				null, null, null);
		cursor.moveToFirst();
		do{
			String name = cursor.getString(cursor.getColumnIndex(BooksDB.B_NAME));
			String author = cursor.getString(cursor.getColumnIndex(BooksDB.B_AUTHOR));
			String price = cursor.getString(cursor.getColumnIndex(BooksDB.B_PRICE));
			String str1 = name + "\n" + author + "\n" + price + "ð";
			
			String name_low = name.toLowerCase();
			String str_low = str.toLowerCase();
			
			if (name_low.indexOf(str_low) != -1)
				searched.add(str1);
		} while (cursor.moveToNext());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searched);
		grv.setAdapter(adapter);
	}
	
	private void addToDBFromString(String str){
		String id = "";
		String name = "";
		String author = "";
		String lang = "";
		String size = "";
		String price = "";
		String url = "";
		int i=1;
		while (str.charAt(i)!='\"'){
			id+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			name+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			author+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			lang+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			size+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			price+=str.charAt(i);
			++i;
		}
		i+=3;
		while (str.charAt(i)!='\"'){
			url+=str.charAt(i);
			++i;
		}
		
		ContentValues cv = new ContentValues();		
		cv.put(BooksDB.B_ID, id);
		cv.put(BooksDB.B_NAME, name);
		cv.put(BooksDB.B_AUTHOR, author);
		cv.put(BooksDB.B_LANG, lang);
		cv.put(BooksDB.B_SIZE, size);
		cv.put(BooksDB.B_PRICE, price);
		cv.put(BooksDB.B_URL, url);		
		db.insert(BooksDB.DATABASE_TABLE, null, cv);
	}

}
