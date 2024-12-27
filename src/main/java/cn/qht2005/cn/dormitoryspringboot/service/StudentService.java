package cn.qht2005.cn.dormitoryspringboot.service;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ChooseBedDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.StudentUpdatePasswordDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.ChooseBed;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetAlreadyChooseBedVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetOccupiedBedVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.StudentLoginVo;

import java.util.List;

public interface StudentService {
	/**
	 * 学生登录
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	StudentLoginVo login(String studentNumber, String password);

	/**
	 * 获取学生可选宿舍信息
	 * @param className
	 * @param gender
	 * @return
	 */
	List<PlanDormitoryVo>getPlanDormitory(String className, Integer gender);

	/**
	 * 选择床位
	 * @param chooseBedDto
	 */
	void insertChooseBed(ChooseBedDto chooseBedDto);

	/**
	 * 获取学生已选床位
	 * @param studentNumber
	 * @return
	 */
	GetAlreadyChooseBedVo getAlreadyChooseBed(String studentNumber);

	/**
	 * 获根据宿舍id列表已被选择的床位
	 * @param dormitoryIds 宿舍id列表
	 * @return
	 */
	List<GetOccupiedBedVo> getOccupiedBed(List<Long> dormitoryIds);

	/**
	 * 修改登录密码
	 * @param studentUpdatePasswordDto
	 */
	void updateLoginPassword(StudentUpdatePasswordDto studentUpdatePasswordDto);
}
