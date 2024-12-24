package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryVo {
	// 主键
	private Long id;
	// 宿舍名称
	private String dormitoryName;
	//  楼栋名称
	private String buildingName;
	// 区域名称
	private String areaName;
	// 床位数
	private Integer bedAmount;
	// 入住人数
	private Integer checkInNumber;
}


