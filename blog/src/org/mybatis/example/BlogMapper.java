package org.mybatis.example;

//import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
	//@Select("SELECT * FROM blog WHERE id = #{id}")
//	Blog selectBlogWithAnnotations(Integer id);
	Blog selectBlog(Integer id);
	Blog selectBlogWithAuthor(Integer id);
}
