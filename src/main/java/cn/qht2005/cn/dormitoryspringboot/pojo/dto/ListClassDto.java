package cn.qht2005.cn.dormitoryspringboot.pojo.dto;

import lombok.Data;

@Data
public class ListClassDto {
	private Integer page;
	private Integer pageSize;
	private String className;
	private String classCode;
	private String collegeName;
	private String grade;
	private Integer boyAmountStart;
	private Integer boyAmountEnd;
	private Integer girlAmountStart;
	private Integer girlAmountEnd;
	private String teacherPhoneNumber;
	//	private Integer planDormitory;
	// 是否分配完毕
	private Integer isPlanComplete;

}
