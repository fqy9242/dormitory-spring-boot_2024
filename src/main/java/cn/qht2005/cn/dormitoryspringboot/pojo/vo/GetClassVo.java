package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;


@Data
public class GetClassVo {
	private Long id;
	private String className;
	private String classCode;
	private String collegeName;
	private String grade;
	private Integer boyAmount;
	private Integer girlAmount;
	private String teacherPhoneNumber;
	// 男生已分配宿舍人数
	private Integer boyAlreadyPlanDormitoryAmount;
	// 女生已分配人数
	private Integer girlAlreadyPlanDormitoryAmount;
}
