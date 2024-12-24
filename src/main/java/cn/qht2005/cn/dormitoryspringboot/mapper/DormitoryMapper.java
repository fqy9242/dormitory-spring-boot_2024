package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Dormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.DormitoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DormitoryMapper {
	/**
	 *  根据id查询宿舍
	 */
	@Select("select dormitory.id,dormitory.check_in_number, dormitory.bed_amount, CONCAT(building.area_name, building.building_name, dormitory.dormitory_number) as dormitory_name, " +
			"building.building_name, building.area_name, dormitory.bed_amount  from dormitory inner join building " +
			"on dormitory.building_id = building.id where dormitory.id = #{id} and dormitory.is_delete = 0")
	DormitoryVo selectDetailById(Long id);

	/**
	 * 根据宿舍id和班级名称查询宿舍
	 * @param dormitoryId
	 * @param className
	 * @return
	 */
	@Select("select * from plan_dormitory where dormitory_id = #{dormitoryId} and class_name = #{className} and is_delete = 0")
	PlanDormitory selectPlanDormitoryByIdAndClassName(Long dormitoryId, String className);
	/**
	 * 根据楼栋id查询宿舍
	 */
	@Select("select * from dormitory where building_id = #{buildingId} and is_delete = 0")
	List<Dormitory> selectByBuildingId(Integer buildingId);

	/**
	 *  入住人数 + 1
	 * @param dormitoryId 宿舍id
	 */
	@Update("update dormitory set check_in_number = check_in_number + 1 where id = #{dormitoryId}")
	void updateCheckInNumberPlus(Long dormitoryId);

	/**
	 * 入住人数 - 1
	 * @param id
	 */
	@Update("update dormitory set check_in_number = check_in_number - 1 where id = #{id}")
	void updateCheckInNumberMinus(long id);
}
