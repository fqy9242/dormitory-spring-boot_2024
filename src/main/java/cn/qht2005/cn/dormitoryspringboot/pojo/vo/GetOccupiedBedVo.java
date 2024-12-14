package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class GetOccupiedBedVo {
	// 宿舍id
	private Long dormitoryId;
	// 已被选择的床位号
	private List<String> occupiedBedRange;
}
