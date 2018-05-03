package com.biz.lesson.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.AccessLogService;
import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.util.PageControl;
import com.biz.lesson.vo.user.AccessLogSearchVo;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("manage/accesslog")
public class AccessLogController extends BaseController {

	@Autowired
	private AccessLogService logService;

	@RequestMapping("/search")
	@PreAuthorize("hasAuthority('OPT_ACCESSLOG_SEARCH')")
	public ModelAndView search(AccessLogSearchVo vo, BindingResult result, HttpServletRequest request) throws Exception {
		error(result, request);

		ModelAndView modelAndView = new ModelAndView("manage/accesslog/search");

		PageControl pc = new PageControl(request, 200);
		modelAndView.addObject("pageControl", pc);

		PageRequest pageRequest = new PageRequest(pc.getCurrentPage() - 1, pc.getPageSize(), new Sort(Direction.DESC, "timestamp"));
		Page<AccessLogPo> accesslogs = logService.searchAccessLog(vo, pageRequest);
		pc.setCount(accesslogs.getTotalElements());

		modelAndView.addObject("accesslogs", accesslogs);
		modelAndView.addObject("vo", vo);
		return modelAndView;
	}

}
