package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDormitory {
	private Long id;
	// 宿舍id
	private Long dormitoryId;
	// 宿舍类型(男/女  -> 1/2)
	private Integer dormitoryType;
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
	// 是否删除
	private Integer delete;
}
