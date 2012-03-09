package org.mybatis.example;

import java.io.Reader;

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
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Author a = (Author) session.selectOne(arg0);
		} finally {
			session.close();
		}
	}
	
	public static void lookUpBlogOldWay() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Blog blog = (Blog) session.selectOne(
					"org.mybatis.example.BlogMapper.selectBlog", 101);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	public static void lookUpBlog() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			System.out.println(mapper.getClass());
			Blog blog = mapper.selectBlog(101);
			System.out.println(blog);
		} finally {
			session.close();
		}

	}

	public static void main(String[] args) throws Exception {
		initSessionFactory();
		lookUpAuthorOldWay();
//		lookUpBlogOldWay();
//		lookUpBlog();
		System.out.println("EOP");
	}
}