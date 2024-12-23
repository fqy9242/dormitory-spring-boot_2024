package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuildingMapper {
	/**
	 * 获取楼栋列表根据宿舍区域
	 */
	@Select("select * from building where area_name = (select area_name from dormitory_area where id = #{areaId}) and is_delete = 0")
	List<Building> selectByAreaId(Integer areaId);
}
