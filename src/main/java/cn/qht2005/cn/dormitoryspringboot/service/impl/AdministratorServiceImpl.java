package cn.qht2005.cn.dormitoryspringboot.service.impl;

import cn.qht2005.cn.dormitoryspringboot.constant.MessageConstant;
import cn.qht2005.cn.dormitoryspringboot.exception.LoginFailException;
import cn.qht2005.cn.dormitoryspringboot.mapper.*;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Administrator;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Class;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.PlanDormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetClassVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryDetailVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.properties.JwtProperties;
import cn.qht2005.cn.dormitoryspringboot.service.AdministratorService;
import cn.qht2005.cn.dormitoryspringboot.utils.JwtUtil;
import cn.qht2005.cn.dormitoryspringboot.utils.result.PageResult;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.javassist.ClassMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {
	@Autowired
	private AdministratorMapper administratorMapper;
	@Autowired
	private JwtProperties jwtProperties;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClassMapper classMapper;
	@Autowired
	private PlanDormitoryMapper planDormitoryMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	/**
	 * 管理员登录
	 *
	 * @param username 用户名
	 * @param password 登录密码
	 * @return VO对象
	 */
	@Override
	public AdministratorLoginVo login(String username, String password) {
		Administrator administrator = administratorMapper.selectByUsernameAndPassword(username, password);
		if (administrator == null) {
			// 根据用户名以及登录密码查询不到管理员对象 -> 账号或密码不存在
			throw new LoginFailException(MessageConstant.USERNAME_OR_PASSWORD_ERROR);
		}
		// 创建一个VO对象
		AdministratorLoginVo administratorLoginVo = new AdministratorLoginVo();
		// 将管理员对象的属性值拷贝到VO对象中
		 BeanUtils.copyProperties(administrator, administratorLoginVo);
		 // 生成token
		Map<String, Object> claim = new HashMap();
		claim.put("id", administrator.getId());
		String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtlMillis(), claim);
		administratorLoginVo.setToken(token);
		return administratorLoginVo;
	}

	/**
	 * 获取班级列表
	 */
	public PageResult listClass(ListClassDto listClassDto) {
		// 设置分页参数
		PageHelper.startPage(listClassDto.getPage(), listClassDto.getPageSize());
		// 获取班级列表
		Page<Class> page = classMapper.pageList(listClassDto);
		// 处理为VO集合
		List<GetClassVo> result = page.getResult().stream().map(classEntry -> {
			GetClassVo getClassVo = new GetClassVo();
			// 拷贝属性
			BeanUtils.copyProperties(classEntry, getClassVo);
			// 设置男生已分配宿舍人数
			Integer boyAlreadyPlan = planDormitoryMapper.sumPlanNumberByClassNameAndGender(classEntry.getClassName(), 1);
			Integer girlAlreadyPlan = planDormitoryMapper.sumPlanNumberByClassNameAndGender(classEntry.getClassName(), 2);
			getClassVo.setBoyAlreadyPlanDormitoryAmount(boyAlreadyPlan == null ? 0 : boyAlreadyPlan);
			getClassVo.setGirlAlreadyPlanDormitoryAmount(girlAlreadyPlan == null ? 0 : girlAlreadyPlan);
			// 设置是否全部分配
//			getClassVo.setIsAllPlan((getClassVo.getBoyAlreadyPlanDormitoryAmount() + getClassVo.getGirlAlreadyPlanDormitoryAmount())
//					== (classEntry.getBoyAmount() + classEntry.getGirlAmount()) ? 1 : 0);
			return getClassVo;
		}).collect(Collectors.toList());
		return new PageResult(page.getTotal(), result);
	}

	/**
	 * 获取学院名称列表
	 */
	@Override
	public List<String> listCollegeName() {
		return collegeMapper.selectCollegeNameList();
	}

	@Override
	public List<PlanDormitoryDetailVo> listPlanDormitoryByClassName(String className) {
		// 1. 根据班级名称查询宿舍分配列表
		List<PlanDormitoryDetailVo> planDormitories = planDormitoryMapper.selectDetailByClassName(className);
		return planDormitories;
	}
}
