package mybatis101;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {

	private static SqlSessionFactory sessionFactory;

	public static void initSessionFactory() throws Exception {
		Reader rdr = Resources.getResourceAsReader("MyBatis101-config.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(rdr);
		rdr.close();
	}

	// uses the old iBATIS style of lookup
	public static void lookUpUserOldWay() throws Exception {
		SqlSession session = sessionFactory.openSession();
		try {
			User user = (User) session.selectOne("mybatis101.Mapper.getUser", Integer.valueOf(1));
			System.out.println(user.getName());  // should print out "User1"

		} finally {
			session.close();
		}
	}

	// uses the new MyBatis style of lookup
	public static void lookUpUser() throws Exception {
		SqlSession session = sessionFactory.openSession();

		try {
			Mapper mapper = session.getMapper(Mapper.class);
			User user = mapper.getUser(2);
			System.out.println(user.getName());  // should print out "User2"
		
		} finally {
			session.close();		
		}
	}
	
	public static void main(String[] args) throws Exception {
		initSessionFactory();
		lookUpUserOldWay();
		lookUpUser();
	}
}
