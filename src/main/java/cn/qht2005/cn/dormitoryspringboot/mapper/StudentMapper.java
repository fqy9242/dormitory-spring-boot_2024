package cn.qht2005.cn.dormitoryspringboot.mapper;

import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
	/**
	 * 根据学号和密码查询学生信息
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	@Select("select id, name, student_number, gender, class_name, bed_number from student " +
			"where student_number = #{studentNumber} and login_password = #{password} and is_delete = 0")
	Student selectByStudentNumberAndPassword(String studentNumber, String password);

	/**
	 * 根据学号查询学生信息
	 * @param student
	 */
	void updateByStudent(Student student);
}
