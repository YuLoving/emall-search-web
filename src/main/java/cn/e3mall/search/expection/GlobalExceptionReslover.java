package cn.e3mall.search.expection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**  

* <p>Title: GlobalExceptionReslover</p>  

* <p>Description:全局异常类 </p>  

* @author 赵天宇

* @date 2018年12月28日  

*/
public class GlobalExceptionReslover implements HandlerExceptionResolver {

	private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionReslover.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//打印控制台
		ex.printStackTrace();
		//写日志
		logger.debug("测试输出的日志");
		logger.info("系统发生异常了。。。");
		logger.error( "系统发生异常", ex);
		//发邮件、打电话
				//使用jmail工具包，打电话就是需要调用第三方的webservice
		//显示错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}

}
