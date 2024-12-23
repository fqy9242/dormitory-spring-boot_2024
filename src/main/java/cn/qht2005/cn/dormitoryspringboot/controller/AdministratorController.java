package cn.qht2005.cn.dormitoryspringboot.controller;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ListClassDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.Building;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.DormitoryArea;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.AdministratorLoginVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryDetailVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
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
	 *  根据宿舍区域获取楼栋列表
	 */
	@GetMapping("/listBuildingByArea")
	public Result<List<Building>> listBuildingByArea(Integer areaId) {
		log.info("获取楼栋列表:{}", areaId);
		List<Building> buildingList = administratorService.listBuildingByArea(areaId);
		return Result.success(buildingList);
	}
}
