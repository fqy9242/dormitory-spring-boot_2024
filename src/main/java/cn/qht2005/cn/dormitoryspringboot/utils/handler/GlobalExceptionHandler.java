package cn.qht2005.cn.dormitoryspringboot.utils.handler;



import cn.qht2005.cn.dormitoryspringboot.exception.BaseException;
import cn.qht2005.cn.dormitoryspringboot.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 *  统一处理业务异常
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	public Result exceptionHandler(BaseException exception) {
		log.error("捕捉到异常:{}", exception.getMessage());
		return Result.error(exception.getMessage());
	}
}
