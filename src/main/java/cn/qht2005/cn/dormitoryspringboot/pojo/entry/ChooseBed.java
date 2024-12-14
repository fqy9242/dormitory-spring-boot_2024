package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChooseBed {
	// id
	private Long id;
	// 宿舍id
//	private Long dormitoryId;
	// 学号
	private String studentNumber;
	// 床位号（规则 -> 宿舍id + 床位序号）
	private String bedNumber;
	// 创建时间
	private LocalDateTime createTime;
	// 更新时间
	private LocalDateTime updateTime;
	// 是否删除
	private Integer delete;
}
