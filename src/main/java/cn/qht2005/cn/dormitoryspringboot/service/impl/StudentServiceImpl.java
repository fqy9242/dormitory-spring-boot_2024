package cn.qht2005.cn.dormitoryspringboot.service.impl;
import cn.qht2005.cn.dormitoryspringboot.constant.MessageConstant;
import cn.qht2005.cn.dormitoryspringboot.exception.BaseException;
import cn.qht2005.cn.dormitoryspringboot.exception.LoginFailException;
import cn.qht2005.cn.dormitoryspringboot.mapper.ChooseBedMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.DormitoryMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.PlanDormitoryMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.StudentMapper;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ChooseBedDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.StudentUpdatePasswordDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.ChooseBed;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Dormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Student;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.*;
import cn.qht2005.cn.dormitoryspringboot.properties.JwtProperties;
import cn.qht2005.cn.dormitoryspringboot.service.AdministratorService;
import cn.qht2005.cn.dormitoryspringboot.service.StudentService;
import cn.qht2005.cn.dormitoryspringboot.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	@Autowired
	private ChooseBedMapper chooseBedMapper;
	@Autowired
	private AdministratorService administratorService;
	@Value("${dormitory.origin-password}")
	private String originPassword;
	/**
	 * 学生登录
	 */
	@Override
	public StudentLoginVo login(String studentNumber, String password) {
		Student student = studentMapper.selectByStudentNumberAndPassword(studentNumber, password);
		if (student == null) {
			// 根据学号跟密码查询不到学生信息
			throw new LoginFailException(MessageConstant.STUDENTNUMBER_OR_PASSWORD_ERROR);
		}
		// 判断该学生的账号是否为默认密码 如果是 则让其修改密码
		if (password.equals(originPassword)) {
			throw new LoginFailException(MessageConstant.PLEASE_MODIFY_PASSWORD);
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
		planDormitoryVo.setBedAmount(dormitoryVo.getBedAmount());
		return planDormitoryVo;
		}).toList();
		return planDormitoryVos;
	}

	/**
	 * 选择床位
	 *
	 * @param chooseBedDto
	 */
	@Override
	@Transactional
	public void insertChooseBed(ChooseBedDto chooseBedDto) {
		// 判断现在是否可以选床位
		Integer chooseBedStatus = administratorService.getChooseBedStatus();
		if (chooseBedStatus == 0) {
			throw new BaseException(MessageConstant.CAN_NOT_CHOOSE_BED);
		}
		// 生成床位号
		String bedNumber = chooseBedDto.getDormitoryId() + "-" + chooseBedDto.getBedRange();

		// 判断是否有人选
		ChooseBed res = chooseBedMapper.selectByBedNumber(bedNumber);
		if (res != null) {
			throw new BaseException(MessageConstant.THIS_BED_IS_OCCUPIED);
		}

		// 获取学生信息
		Student student = studentMapper.selectByStudentNumber(chooseBedDto.getStudentNumber());

		// 获取分配表信息
		PlanDormitory planDormitory = dormitoryMapper.selectPlanDormitoryByIdAndClassName(chooseBedDto.getDormitoryId(), student.getClassName());
		if (planDormitory.getPlanNumber() <= planDormitory.getChooseNumber()) {
			throw new BaseException(MessageConstant.DORMITORY_IS_FULL);
		}

		// 释放学生原来选的宿舍名额
		releaseOldBed(student);

		// 更新当前宿舍的选择人数
		updatePlanDormitoryChooseNumber(student.getClassName(), chooseBedDto.getDormitoryId(), 1);

		// 更新床位选择表
		ChooseBed chooseBed = new ChooseBed();
		chooseBed.setBedNumber(bedNumber);
		chooseBed.setStudentNumber(chooseBedDto.getStudentNumber());
		chooseBed.setCreateTime(LocalDateTime.now());
		chooseBed.setUpdateTime(LocalDateTime.now());
		chooseBedMapper.insertChooseBed(chooseBed);

		// 更新宿舍表/入住人数 + 1
		dormitoryMapper.updateCheckInNumberPlus(chooseBedDto.getDormitoryId());

		// 更新学生信息
		student.setBedNumber(bedNumber);
		student.setUpdateTime(LocalDateTime.now());
		studentMapper.updateByStudent(student);
	}
	/**
	 * 释放学生原来选的宿舍名额
	 * @param student
	 */
	private void releaseOldBed(Student student) {
		String studentOldBedNumber = student.getBedNumber();
		if (studentOldBedNumber != null) {
			chooseBedMapper.updateToDeleteByStudentNumber(student.getStudentNumber());
			PlanDormitory oldPlanDormitory = planDormitoryMapper.selectByClassNameAndDormitoryId(student.getClassName(), Long.parseLong(studentOldBedNumber.split("-")[0]));
			oldPlanDormitory.setChooseNumber(oldPlanDormitory.getChooseNumber() - 1);
			planDormitoryMapper.updateByEntry(oldPlanDormitory);
			dormitoryMapper.updateCheckInNumberMinus(Long.parseLong(studentOldBedNumber.split("-")[0]));
		}
	}
	/**
	 * 更新宿舍选择人数
	 * @param className
	 * @param dormitoryId
	 * @param increment
	 */
	private void updatePlanDormitoryChooseNumber(String className, Long dormitoryId, int increment) {
		PlanDormitory planDormitory = planDormitoryMapper.selectByClassNameAndDormitoryId(className, dormitoryId);
		planDormitory.setChooseNumber(planDormitory.getChooseNumber() + increment);
		planDormitoryMapper.updateByEntry(planDormitory);
	}

	/**
	 * 获取学生已选床位
	 *
	 * @param studentNumber
	 * @return
	 */
	@Override
	public GetAlreadyChooseBedVo getAlreadyChooseBed(String studentNumber) {
		// 从数据库中查询学生已选床位信息
		ChooseBed chooseBed = chooseBedMapper.selectByStudentNumber(studentNumber);
		if (chooseBed == null) return null;
		// 将ChooseBed转换为GetAlreadyChooseBedVo
		GetAlreadyChooseBedVo getAlreadyChooseBedVo = new GetAlreadyChooseBedVo();
		BeanUtils.copyProperties(chooseBed, getAlreadyChooseBedVo);
		// 获取宿舍详细信息并封装到vo
		String[] split = chooseBed.getBedNumber().split("-");
		DormitoryVo dormitoryVo = dormitoryMapper.selectDetailById(Long.parseLong(split[0]));
		getAlreadyChooseBedVo.setDormitoryName(dormitoryVo.getDormitoryName());
		getAlreadyChooseBedVo.setBedRange(split[1]);
		getAlreadyChooseBedVo.setDormitoryId(Long.parseLong(split[0]));
		return getAlreadyChooseBedVo;
	}

	/**
	 *  根据宿舍号生成所有的床位号集合
	 * @param dormitoryId 宿舍id
	 * @return
	 */
	public List<String> generateBedNumberList(Long dormitoryId) {
		// 获取床位数量
		DormitoryVo dormitoryVo = dormitoryMapper.selectDetailById(dormitoryId);
		// 生成床位号列表
		List<String> bedNumberList = new ArrayList<>();
		for (int i = 1; i <= dormitoryVo.getBedAmount(); i++) {
			// 生成床位号
			bedNumberList.add(dormitoryId + "-" + i);
		}
		return bedNumberList;
	}

	/**
	 * 获根据宿舍id列表已被选择的床位
	 *
	 * @param dormitoryIds 宿舍id列表
	 * @return
	 */
	@Override
	public List<GetOccupiedBedVo> getOccupiedBed(List<Long> dormitoryIds) {
		// 创建返回结果列表
		List<GetOccupiedBedVo> occupiedBedVos = new ArrayList<>();
		// 生成床位号列表
		for (Long dormitoryId : dormitoryIds) {
			List<String> bedNumberList = generateBedNumberList(dormitoryId);
			// 查询已被选择的床位号
			List<String> occupiedBedList = chooseBedMapper.selectBedNumbersIfExist(bedNumberList);
			// 将已被选择的床位号转换为GetOccupiedBedVo
			occupiedBedVos.add(GetOccupiedBedVo
							.builder()
							.dormitoryId(dormitoryId)
							.occupiedBedRange(occupiedBedList)
							.build()
			);
		}
		return occupiedBedVos;
	}

	/**
	 * 修改登录密码
	 *
	 * @param studentUpdatePasswordDto
	 */
	@Override
	public void updateLoginPassword(StudentUpdatePasswordDto studentUpdatePasswordDto) {
		// 判断原密码是否正确
		Student student = studentMapper.selectByStudentNumberAndPassword(studentUpdatePasswordDto.getStudentNumber(), studentUpdatePasswordDto.getOldPassword());
		if (student == null) {
			throw new BaseException(MessageConstant.OLD_PASSWORD_ERROR);
		}
		// 修改密码
		student.setLoginPassword(studentUpdatePasswordDto.getNewPassword());
		student.setUpdateTime(LocalDateTime.now());
		studentMapper.updateByStudent(student);
	}
}
