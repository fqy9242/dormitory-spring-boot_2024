package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryArea {
	private Long id;
	private String areaName;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer delete;
}
