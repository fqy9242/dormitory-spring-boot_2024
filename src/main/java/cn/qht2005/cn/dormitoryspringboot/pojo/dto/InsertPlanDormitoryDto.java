package cn.qht2005.cn.dormitoryspringboot.pojo.dto;

import lombok.Data;

@Data
public class InsertPlanDormitoryDto {
	private Integer dormitoryId;
	private Integer dormitoryType;
	private String className;
	private Integer planNumber;
}
