package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlanDormitoryMapper {
	@Select("select * from plan_dormitory where class_name = #{className} and dormitory_type = #{gender} and is_delete = 0")
	List<PlanDormitory> selectByClassNameAndGender(String className, Integer gender);

	/**
	 * 根据班级名称和性别统计人数
	 * @param className
	 * @param gender
	 */
	@Select("SELECT SUM(plan_dormitory.plan_number)  FROM `plan_dormitory` WHERE plan_dormitory.class_name = #{className} AND plan_dormitory.dormitory_type = #{gender} AND plan_dormitory.is_delete = 0")
	Integer sumPlanNumberByClassNameAndGender(String className, Integer gender);

	void updateByEntry(PlanDormitory planDormitoryOld);
	@Select("select * from plan_dormitory where class_name = #{className} and dormitory_id = #{dormitoryId} and is_delete = 0")
	PlanDormitory selectByClassNameAndDormitoryId(String className, long dormitoryId);
	/**
	 * 根据班级名称查询
	 */
	@Select("SELECT plan_dormitory.id, building.area_name, building.building_name, dormitory.dormitory_number,plan_dormitory.dormitory_type, plan_dormitory.class_name,plan_dormitory.plan_number\n" +
			"FROM plan_dormitory \n" +
			"INNER JOIN dormitory\n" +
			"ON plan_dormitory.dormitory_id = dormitory.id\n" +
			"INNER JOIN building\n" +
			"ON dormitory.building_id = building.id\n" +
			"WHERE \n" +
			"\tplan_dormitory.class_name = #{className}" +
			"\tAND plan_dormitory.is_delete = 0 \n" +
			"ORDER BY plan_dormitory.dormitory_type")
	List<PlanDormitoryDetailVo> selectDetailByClassName(String className);

/*	*//**
	 * 根据宿舍id查询计划人数
	 * @param dormitoryId
	 * @return
	 *//*
	@Select("SELECT SUM(plan_number) as total_plan_number FROM plan_dormitory WHERE dormitory_id = #{dormitoryId} AND is_delete = 0")
	Integer sumPlanNumberByDormitoryId(Long dormitoryId);*/
}
