package cn.qht2005.cn.dormitoryspringboot.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
	private Long total;
	private Object records;
}
