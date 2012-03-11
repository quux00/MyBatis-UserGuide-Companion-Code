package org.mybatis.example;

public class Blog {
	private Integer id;
	private String title;
	private Author author;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return String.format("[Blog: id=%d, title=%s, author=%s]", id, title, author);
	}
	
}
