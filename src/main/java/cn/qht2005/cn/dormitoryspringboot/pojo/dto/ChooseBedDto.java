package cn.qht2005.cn.dormitoryspringboot.pojo.dto;

import lombok.Data;

@Data
public class ChooseBedDto {
	// 宿舍id
	private Long dormitoryId;
	// 学号
	private String studentNumber;
	// 床位序号
	private Integer bedRange;
}
