package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

@Data
public class PlanDormitoryDetailVo {
	private Long id;
	private String areaName;
	private String buildingName;
	private String dormitoryNumber;
	private String dormitoryType;
	private String className;
	private Integer planNumber;
}
