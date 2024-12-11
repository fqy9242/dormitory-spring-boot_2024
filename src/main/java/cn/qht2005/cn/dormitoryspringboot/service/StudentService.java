package cn.qht2005.cn.dormitoryspringboot.service;

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

}
