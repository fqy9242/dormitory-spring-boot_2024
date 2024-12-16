package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

@Data
public class StudentLoginVo {
	private Long id;
	private String name;
	private String studentNumber;
	private Integer gender;
	private String className;
	private String bedNumber;
	// 紧急联系人电话号码
	private String emergencyContactPhone;
	private String token;
}
