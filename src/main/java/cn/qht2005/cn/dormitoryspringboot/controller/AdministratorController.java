package cn.qht2005.cn.dormitoryspringboot.controller;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.GetBuildingByBuildingDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.InsertPlanDormitoryDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Dormitory;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.DormitoryArea;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.DormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryDetailVo;
import cn.qht2005.cn.dormitoryspringboot.service.AdministratorService;
import cn.qht2005.cn.dormitoryspringboot.utils.result.PageResult;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/administrator")
@Slf4j
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	/**
	 * 管理员登录
	 */
	@PostMapping("/login")
	public Result<AdministratorLoginVo> login(String username, String password) {
		log.info("管理员登录,{},{}",username, password);
		AdministratorLoginVo administratorLoginVo = administratorService.login(username, password);
		return Result.success(administratorLoginVo);
	}

	/**
	 *  获取班级列表
	 */
	@PostMapping("/listClass")
	public Result<PageResult> listClass(@RequestBody ListClassDto listClassDto) {
		log.info("获取班级列表:{}", listClassDto);
		PageResult classList = administratorService.listClass(listClassDto);
		return Result.success(classList);
	}
	/**
	 * 获取学院名称列表
	 */
	@GetMapping("/listCollegeName")
	public Result<List<String>> listCollegeName() {
		log.info("获取学院名称列表");
		List<String> collegeNameList = administratorService.listCollegeName();
		return Result.success(collegeNameList);
	}
	/**
	 * 获取班级宿舍列表
	 */
	@GetMapping("/listPlanDormitoryByClassName")
	public Result<List<PlanDormitoryDetailVo>> listPlanDormitoryByClassName(String className) {
		log.info("获取班级宿舍列表:{}", className);
		List<PlanDormitoryDetailVo> planDormitoryVoList = administratorService.listPlanDormitoryByClassName(className);
		return Result.success(planDormitoryVoList);
	}
	/**
	 * 获取宿舍区域列表
	 */
	@GetMapping("/listDormitoryArea")
	public Result<List<DormitoryArea>> listDormitoryArea() {
		log.info("获取宿舍区域列表");
		List<DormitoryArea> dormitoryAreaList = administratorService.listDormitoryArea();
		return Result.success(dormitoryAreaList);
	}

	/**
	 *  根据楼栋参数获取楼栋列表
	 */
	@PostMapping("/listBuildingByBuilding")
	public Result<List<Building>> listBuildingByBuilding(@RequestBody GetBuildingByBuildingDto buildingByBuildingDto) {
		log.info("获取楼栋列表:{}",buildingByBuildingDto);
		List<Building> buildingList = administratorService.listBuildingByBuilding(buildingByBuildingDto);
		return Result.success(buildingList);
	}
	/**
	 *  根据楼栋获取宿舍列表
	 */
	@GetMapping("/listDormitoryByBuilding")
	public Result<List<Dormitory>> listDormitoryByBuilding(Integer buildingId) {
		log.info("获取宿舍列表:{}", buildingId);
		List<Dormitory> dormitoryList = administratorService.listDormitoryByBuilding(buildingId);
		return Result.success(dormitoryList);
	}
	/**
	 *  根据id获取宿舍
	 */
	@GetMapping("/getDormitoryById/{id}")
	public Result<DormitoryVo> getDormitoryById(@PathVariable Long id) {
		log.info("根据id获取宿舍:{}", id);
		DormitoryVo dormitoryVo = administratorService.getDormitoryById(id);
		return Result.success(dormitoryVo);
	}

	/**
	 *  分配宿舍
	 * @param insertPlanDormitoryDto
	 * @return
	 */
	@PostMapping("/planDormitory")
	public Result planDormitory(@RequestBody InsertPlanDormitoryDto	insertPlanDormitoryDto) {
		log.info("插入宿舍分配:{}", insertPlanDormitoryDto);
		administratorService.insertPlanDormitory(insertPlanDormitoryDto);
		return Result.success();
	}
	/**
	 *  设置可选床位状态
	 */
	@PutMapping("/setChooseBedStatus/{status}")
	public Result setChooseBedStatus(@PathVariable Integer status) {
		log.info("设置可选床位状态:{}", status);
		administratorService.setChooseBedStatus(status);
		return Result.success();
	}
	/**
	 *  获取可选床位状态
	 */
	@GetMapping("/getChooseBedStatus")
	public Result<Integer> getChooseBedStatus() {
		log.info("获取可选床位状态");
		Integer status = administratorService.getChooseBedStatus();
		return Result.success(status);
	}
}
