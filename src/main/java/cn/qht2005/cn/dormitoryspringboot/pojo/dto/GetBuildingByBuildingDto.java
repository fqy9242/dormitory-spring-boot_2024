package cn.qht2005.cn.dormitoryspringboot.pojo.dto;

import lombok.Data;

@Data
public class GetBuildingByBuildingDto {
	private Long id;
	private String buildingName;
	private Integer type;
	private String areaId;
	private String areaName;
	private Integer floorDormitoryAmount;
	private Integer floorAmount;
	private String principalPhone;
}
