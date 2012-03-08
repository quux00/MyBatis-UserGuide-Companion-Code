package mybatis101;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {

  public static SqlSessionFactory getSessionFactory() throws Exception {
    Reader rdr = Resources.getResourceAsReader("MyBatis101-config.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(rdr);
    rdr.close();
    return sessionFactory;
  }
  
  public static void main(String[] args) throws Exception {
    SqlSessionFactory sessionFactory = getSessionFactory();
    SqlSession session = sessionFactory.openSession();
    Mapper mapper = session.getMapper(Mapper.class);
    User user = mapper.getUser(1);
    
    System.out.println(user.getName());  // should print out "User1"
  }
}
