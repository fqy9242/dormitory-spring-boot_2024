package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {
	/**
	 * 根据用户名和密码查询管理员
	 */
	@Select("select * from administrator where username = #{username} and password = #{password} and is_delete = 0")
	Administrator selectByUsernameAndPassword(String username, String password);
}
