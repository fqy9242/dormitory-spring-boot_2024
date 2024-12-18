package cn.qht2005.cn.dormitoryspringboot.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;
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
}
