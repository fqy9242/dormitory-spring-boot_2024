package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory {
	// 主键
	private Long id;
	// 宿舍号
	private String dormitoryNumber;
	//  楼栋id
	private Long buildingId;
	// 床位数
	private Integer bedNumber;
	// 入住人数
	private Integer checkInNumber;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
	// 是否删除
	private Integer delete;

}
