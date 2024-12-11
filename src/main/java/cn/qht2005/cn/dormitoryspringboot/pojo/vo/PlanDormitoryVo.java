package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

@Data
public class PlanDormitoryVo {
	// 主键
	private Long id;
	// 宿舍名称
	private String dormitoryName;
	// 宿舍类型(男/女)
	private String dormitoryType;
	// 班级名称
	private String className;
	// 安排人数
	private Integer planNumber;
	// 选择人数
	private Integer chooseNumber;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
}
