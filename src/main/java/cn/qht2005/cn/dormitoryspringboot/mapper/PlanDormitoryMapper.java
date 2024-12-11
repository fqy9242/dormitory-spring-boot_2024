package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlanDormitoryMapper {
	@Select("select * from plan_dormitory where class_name = #{className} and dormitory_type = #{gender} and is_delete = 0")
	List<PlanDormitory> selectByClassNameAndGender(String className, Integer gender);
}
