package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private Long id;
	// 姓名
	private String name;
	// 学号
	private String studentNumber;
	// 密码
	private String loginPassword;
	// 性别

	private Integer gender;
	// 班级名称
	private String className;
	// 床位号
	private String bedNumber;
	// 紧急联系人
	private String emergencyContact;
	// 创建时间
	private LocalDateTime createTime;
	// 更新时间
	private LocalDateTime updateTime;
	// 删除标记
	private Integer delete;

}