package cn.qht2005.cn.dormitoryspringboot.pojo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
	private Long id;
	private String buildingName;
	private Integer type;
	private String areaName;
	private Integer floor_dormitory_amount;
	private Integer floor_amount;
	private String principalPhone;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer delete;

}
