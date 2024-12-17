package cn.qht2005.cn.dormitoryspringboot.service;

import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;

public interface AdministratorService {
	/**
	 * 管理员登录
	 * @param username 用户名
	 * @param password 登录密码
	 * @return VO对象
	 */
	AdministratorLoginVo login(String username, String password);
}
