package org.mybatis.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlogMapperTests {

	static SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String resource = "org/mybatis/example/Configuration.xml";
		Reader rdr = Resources.getResourceAsReader(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(rdr);
	}

	@Before
	public void setUp() throws Exception {
		session = sqlSessionFactory.openSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void testSelectBlog() {
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		Blog blog = mapper.selectBlog(1);
		assertEquals(Integer.valueOf(1), blog.getId());
		assertNull(blog.getAuthor());
		assertEquals("Why I am \"The Dude\"", blog.getTitle());
	}

}
