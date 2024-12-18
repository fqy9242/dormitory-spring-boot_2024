package cn.qht2005.cn.dormitoryspringboot.service.impl;

import cn.qht2005.cn.dormitoryspringboot.constant.MessageConstant;
import cn.qht2005.cn.dormitoryspringboot.exception.LoginFailException;
import cn.qht2005.cn.dormitoryspringboot.mapper.AdministratorMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.ClassMapper;
import cn.qht2005.cn.dormitoryspringboot.mapper.StudentMapper;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Administrator;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Class;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetClassVo;
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

import java.util.HashMap;
import java.util.Map;

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
	 *
	 * @param lIstClassDto
	 * @return
	 */
	@Override
	public PageResult listClass(ListClassDto lIstClassDto) {
		PageHelper.startPage(lIstClassDto.getPage(), lIstClassDto.getPageSize());
		Page<GetClassVo> page = classMapper.pageList(lIstClassDto);
		PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
		return pageResult;

	}
}
