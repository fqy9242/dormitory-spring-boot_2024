package cn.qht2005.cn.dormitoryspringboot.controller;

import cn.qht2005.cn.dormitoryspringboot.pojo.dto.ChooseBedDto;
import cn.qht2005.cn.dormitoryspringboot.pojo.entry.ChooseBed;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetAlreadyChooseBedVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.GetOccupiedBedVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.StudentLoginVo;
import cn.qht2005.cn.dormitoryspringboot.service.StudentService;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@PostMapping("/login")
	public Result<StudentLoginVo> login(String studentNumber, String password) {
		log.info("学生登录,{},{}", studentNumber, password);
		StudentLoginVo studentLoginVo = studentService.login(studentNumber, password);
		return Result.success(studentLoginVo);
	}
	/**
	 * 获取学生可选宿舍信息
	 * @return
	 */
	@GetMapping("/getPlanDormitory")
	public Result<List<PlanDormitoryVo>> getPlanDormitory(String className, Integer gender) {
		log.info("获取学生可选宿舍信息,{},{}", className, gender);
		List<PlanDormitoryVo> dormitoryVoList = studentService.getPlanDormitory(className, gender);
		return Result.success(dormitoryVoList);
	}
	@PostMapping("/chooseBed")
	public Result insertChooseBed(@RequestBody ChooseBedDto chooseBedDto) {
		log.info("学生选择床位,{}", chooseBedDto);
		studentService.insertChooseBed(chooseBedDto);
		return Result.success();
	}

	/**
	 *  获取学生已选床位
	 * @param studentNumber
	 * @return
	 */
	@GetMapping("/getAlreadyChooseBed")
	public Result<GetAlreadyChooseBedVo> getAlreadyChooseBed(String studentNumber) {
		log.info("获取学生已选床位,{}", studentNumber);
		GetAlreadyChooseBedVo chooseBed = studentService.getAlreadyChooseBed(studentNumber);
		return Result.success(chooseBed);
	}

	/**
	 * 根据宿舍id列表获取因为选择的床位
	 */
	@GetMapping("/getOccupiedBed")
	public Result<List<GetOccupiedBedVo>> getOccupiedBed(@RequestParam List<Long> dormitoryIds) {
		log.info("获取宿舍已被占用位,{}", dormitoryIds);
		List<GetOccupiedBedVo> bedRanges = studentService.getOccupiedBed(dormitoryIds);
		return Result.success(bedRanges);
	}

}
