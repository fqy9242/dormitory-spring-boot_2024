package cn.qht2005.cn.dormitoryspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AreaMapper {
	/**
	 * 查询所有宿舍区域名称
	 * @return
	 */
	@Select("select area_name from dormitory_area where is_delete = 0")
	List<String> selectAllName();

}
