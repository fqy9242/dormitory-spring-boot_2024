package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetAlreadyChooseBedVo {
	// id
	private Long id;
	// 宿舍id
//	private Long dormitoryId;
	// 学号
	private String studentNumber;
	// 宿舍id
	private Long dormitoryId;
	// 宿舍全称(东一区1栋111)
	private String dormitoryName;
	// 床位序号 (1,2,3...)
	private String bedRange;
	// 创建时间
	private LocalDateTime createTime;
}
