package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
	private Long id;
	private String className;
	private String classCode;
	private String collegeName;
	private String grade;
	private Integer boyAmount;
	private Integer girlAmount;
	private String teacherPhoneNumber;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer delete;
}
