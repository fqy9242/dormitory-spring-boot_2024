package cn.qht2005.cn.dormitoryspringboot.service;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.utils.result.PageResult;

public interface AdministratorService {
	/**
	 * 管理员登录
	 * @param username 用户名
	 * @param password 登录密码
	 * @return VO对象
	 */
	AdministratorLoginVo login(String username, String password);

	/**
	 * 获取班级列表
	 * @param lIstClassDto
	 * @return
	 */
	PageResult listClass(ListClassDto lIstClassDto);
}
