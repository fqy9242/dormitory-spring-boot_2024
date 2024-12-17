package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

@Data

public class AdministratorLoginVo {
	// 主键
	private Integer id;
	// 用户名
	private String username;
	// 登录密码
	private String password;
	// 创建时间
	private String createTime;
	// token令牌
	private String token;
}
