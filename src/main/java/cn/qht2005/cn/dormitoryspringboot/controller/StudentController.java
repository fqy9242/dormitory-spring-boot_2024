package cn.qht2005.cn.dormitoryspringboot.controller;

import cn.qht2005.cn.dormitoryspringboot.pojo.vo.PlanDormitoryVo;
import cn.qht2005.cn.dormitoryspringboot.pojo.vo.StudentLoginVo;
import cn.qht2005.cn.dormitoryspringboot.service.StudentService;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
