package org.mybatis.example;

import java.io.Reader;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {

	static SqlSessionFactory sqlSessionFactory;

	public static void initSessionFactory() throws Exception {
		String resource = "org/mybatis/example/Configuration.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}

	public static void lookUpAuthorOldWay() {
		System.out.println("/* ---[ lookUpAuthorOldWay ]--- */");

		Author au = retrieveAuthorById(1);
		System.out.println(au);
	}

	private static Author retrieveAuthorById(Integer id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return (Author) session.selectOne(
					         "org.mybatis.example.AuthorMapper.getAuthor", 
          		             id);
		} finally {
			session.close();
		}
	}
	
	private static Author createAuthor() {
		Random r = new Random();
		Author a = new Author();
		a.setUsername("foo" + r.nextInt(9999999));
		a.setHashedPassword("pass" + r.nextInt());
		a.setEmail(String.format("foo.%d@pobox.com", r.nextInt(6666)));
		//a.setBio("I voted for Pedro");
		return a;
	}
	
	public static void insertAuthor() {
		System.out.println("/* ---[ insertAuthor ]--- */");

		Author newAuthor = createAuthor();
		SqlSession session = sqlSessionFactory.openSession();

		try {
			int rt = session.insert("org.mybatis.example.AuthorMapper.insertAuthor", newAuthor);
			if (rt == 0) {
				System.err.println("Insert into Author failed");
			}
			session.commit();
		} finally {
			session.close();
		}
		Author a = retrieveAuthorById(newAuthor.getId());
		System.out.println(a.getUsername().equals(newAuthor.getUsername()));
	}
	
	public static void lookUpBlogOldWay() {
		System.out.println("/* ---[ lookUpBlogOldWay ]--- */");
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Blog blog = (Blog) session.selectOne(
					"org.mybatis.example.BlogMapper.selectBlog", 1);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	public static void lookUpBlogWithAuthorOldWay() {
		System.out.println("/* ---[ lookUpBlogWithAuthorOldWay ]--- */");
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Blog blog = (Blog) session.selectOne(
					"org.mybatis.example.BlogMapper.selectBlogWithAuthor", 1);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	public static void lookUpBlog() {
		System.out.println("/* ---[ lookUpBlog ]--- */");

		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			Blog blog = mapper.selectBlog(2);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}
	
	public static void lookUpBlogWithAnnotations() {
		System.out.println("/* ---[ lookUpBlogWithAnnotations]--- */");

		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			Blog blog = mapper.selectBlogWithAnnotations(1);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}
	
	public static void lookUpBlogWithAuthor() {
		System.out.println("/* ---[ lookUpBlogWithAuthor ]--- */");
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			Blog blog = mapper.selectBlogWithAuthor(2);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	/**
	 * Exercises a few basic methods for lookups and inserts for Author and Blog 
	 * objects/tables/queries
	 */
	public static void main(String[] args) throws Exception {
		initSessionFactory();
		lookUpAuthorOldWay();
		lookUpBlogOldWay();
		lookUpBlogWithAuthorOldWay();
		lookUpBlog();
		lookUpBlogWithAuthor();
		lookUpBlogWithAnnotations();
		insertAuthor();
		System.out.println("EOP");
	}
}