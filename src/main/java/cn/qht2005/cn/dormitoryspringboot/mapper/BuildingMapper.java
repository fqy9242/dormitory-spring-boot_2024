package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.GetBuildingByBuildingDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuildingMapper {
	/**
	 * 根据楼栋实体获取楼栋列表
	 */
	List<Building> selectByBuilding(GetBuildingByBuildingDto buildingByBuildingDto);
}
