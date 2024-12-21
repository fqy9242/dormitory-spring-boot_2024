package cn.qht2005.cn.dormitoryspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CollegeMapper {
	@Select("select college_name from college where is_delete = 0")
	List<String> selectCollegeNameList();
}
