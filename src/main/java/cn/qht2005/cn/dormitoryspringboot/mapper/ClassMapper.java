package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Class;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetClassVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {
	/**
	 * 根据条件分页获取班级列表
	 * @param lIstClassDto
	 * @return
	 */
	Page<GetClassVo> pageList(ListClassDto lIstClassDto);
}
