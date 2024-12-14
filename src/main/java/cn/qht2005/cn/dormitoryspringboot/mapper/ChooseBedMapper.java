package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.ChooseBed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
/**
 *  选床位表mapper
 */
public interface ChooseBedMapper {
	/**
	 * 插入选床位信息
	 */
	void insertChooseBed(ChooseBed chooseBed);

	/**
	 *  根据床位号查询
	 * @param bedNumber
	 * @return
	 */
	@ResultMap("chooseBedResultMapper")
	@Select("select * from choose_bed where bed_number = #{bedNumber} and is_delete = 0")
	ChooseBed selectByBedNumber(String bedNumber);

	/**
	 *  根据学号逻辑删除该学生床位信息
	 */
	@Update("update choose_bed set is_delete = 1 where student_number = #{studentNumber}")
	void updateToDeleteByStudentNumber(String studentNumber);

	/**
	 * 根据学号查询学生床位信息
	 * @param studentNumber
	 * @return
	 */
	@ResultMap("chooseBedResultMapper")
	@Select("select * from choose_bed where student_number = #{studentNumber} and is_delete = 0")
	ChooseBed selectByStudentNumber(String studentNumber);
}
