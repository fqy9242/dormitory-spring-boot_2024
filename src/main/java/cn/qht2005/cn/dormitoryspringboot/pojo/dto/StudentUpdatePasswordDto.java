package cn.qht2005.cn.dormitoryspringboot.pojo.dto;

import lombok.Data;

@Data
public class StudentUpdatePasswordDto {
	private String studentNumber;
	private String oldPassword;
	private String newPassword;
}
