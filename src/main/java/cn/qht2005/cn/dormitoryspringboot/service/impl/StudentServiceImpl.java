package cn.qht2005.cn.dormitoryspringboot.service.impl;
import cn.qht2005.cn.dormitoryspringboot.constant.MessageConstant;
import cn.qht2005.cn.dormitoryspringboot.exception.LoginFailException;
import cn.qht2005.cn.dormitoryspringboot.mapper.DormitoryMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.PlanDormitoryMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.StudentMapper;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Dormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Student;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.DormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.StudentLoginVo;
import cn.qht2005.cn.dormitoryspringboot.properties.JwtProperties;
import cn.qht2005.cn.dormitoryspringboot.service.StudentService;
import cn.qht2005.cn.dormitoryspringboot.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private JwtProperties jwtProperties;
	@Autowired
	private PlanDormitoryMapper planDormitoryMapper;
	@Autowired
	private DormitoryMapper dormitoryMapper;
	/**
	 * 学生登录
	 * @param studentNumber
	 * @param password
	 * @return
	 */
	@Override
	public StudentLoginVo login(String studentNumber, String password) {
		Student student = studentMapper.selectByStudentNumberAndPassword(studentNumber, password);
		if (student == null) {
			// 根据学号跟密码查询不到学生信息
			throw new LoginFailException(MessageConstant.STUDENTNUMBER_OR_PASSWORD_ERROR);
		}
		StudentLoginVo studentLoginVo = new StudentLoginVo();
		// 将学生信息封装到StudentLoginVo中
		BeanUtils.copyProperties(student, studentLoginVo);
		// 生成token
		Map<String, Object> claim = new HashMap();
		claim.put("id", student.getId());
		String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), claim);
		studentLoginVo.setToken(token);
		return studentLoginVo;
	}

	/**
	 * 获取学生可选宿舍信息
	 *
	 * @param className
	 * @param gender
	 * @return
	 */
	@Override
	public List<PlanDormitoryVo> getPlanDormitory(String className, Integer gender) {
		// 根据班级名称跟性别查询可选宿舍信息
		List<PlanDormitory> planDormitories = planDormitoryMapper.selectByClassNameAndGender(className, gender);
		// 将PlanDormitory转换为PlanDormitoryVo
		List<PlanDormitoryVo> planDormitoryVos = planDormitories.stream().map(planDormitory -> {
		PlanDormitoryVo planDormitoryVo = new PlanDormitoryVo();
		BeanUtils.copyProperties(planDormitory, planDormitoryVo);
		planDormitoryVo.setDormitoryType(planDormitory.getDormitoryType() == 1 ? "男生宿舍" : "女生宿舍");
		// 获取宿舍详细信息并封装到vo
		DormitoryVo dormitoryVo = dormitoryMapper.selectDetailById(planDormitory.getDormitoryId());
		planDormitoryVo.setDormitoryName(dormitoryVo.getDormitoryName());
		return planDormitoryVo;
		}).toList();
		return planDormitoryVos;
	}
}
