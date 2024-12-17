package cn.qht2005.cn.dormitoryspringboot.controller;

import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.service.AdministratorService;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrator")
@Slf4j
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	/**
	 * 管理员登录
	 */
	@PostMapping("/login")
	public Result<AdministratorLoginVo> login(String username, String password) {
		log.info("管理员登录,{},{}",username, password);
		AdministratorLoginVo administratorLoginVo = administratorService.login(username, password);
		return Result.success(administratorLoginVo);
	}
}
