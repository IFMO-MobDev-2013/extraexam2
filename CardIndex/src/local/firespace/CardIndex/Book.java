package local.firespace.CardIndex;

public class Book {
	private int id;
	private String name;
	private String author;
	private String lang;
	private int count;
	private int cost;
	private String url;

	public Book(int id, String name, String author, String lang, int count, int cost, String url) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.lang = lang;
		this.count = count;
		this.cost = cost;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getLang() {
		return lang;
	}

	public int getCount() {
		return count;
	}

	public int getCost() {
		return cost;
	}

	public String getUrl() {
		return url;
	}
}
