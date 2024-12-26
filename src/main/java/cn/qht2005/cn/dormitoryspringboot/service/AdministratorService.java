package cn.qht2005.cn.dormitoryspringboot.service;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.GetBuildingByBuildingDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.InsertPlanDormitoryDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Dormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.DormitoryArea;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.DormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryDetailVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.utils.result.PageResult;

import java.util.List;

public interface AdministratorService {
	/**
	 * 管理员登录
	 * @param username 用户名
	 * @param password 登录密码
	 * @return VO对象
	 */
	AdministratorLoginVo login(String username, String password);

	/**
	 * 获取班级列表
	 * @param lIstClassDto
	 * @return
	 */
	PageResult listClass(ListClassDto lIstClassDto);
	/**
	 * 获取学院名称列表
	 */
	List<String> listCollegeName();

	List<PlanDormitoryDetailVo> listPlanDormitoryByClassName(String className);

	/**
	 * 获取宿舍区域列表
	 */
	List<DormitoryArea> listDormitoryArea();

	/**
	 * 获取楼栋参数获取楼栋列表
	 */
	List<Building> listBuildingByBuilding(GetBuildingByBuildingDto buildingByBuildingDto);

	/**
	 * 获取宿舍列表根据楼栋
	 * @param buildingId
	 * @return
	 */
	List<Dormitory> listDormitoryByBuilding(Integer buildingId);

	/**
	 * 获取宿舍详情
	 * @param id
	 * @return
	 */
	DormitoryVo getDormitoryById(Long id);
	/**
	 * 插入宿舍分配表
	 * @param insertPlanDormitoryDto
	 */
	void insertPlanDormitory(InsertPlanDormitoryDto insertPlanDormitoryDto);

	/**
	 *  设置选床位开放状态
	 * @param status
	 */
	void setChooseBedStatus(Integer status);

	/**
	 * 获取可选床位是否开放选择状态
	 * @return
	 */
	Integer getChooseBedStatus();
}
