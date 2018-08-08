package com.xgb.org.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xgb.org.common.BootStrapPager;
import com.xgb.org.common.JsonUtils;
import com.xgb.org.common.StringUtils;
import com.xgb.org.domain.UserTemp;
import com.xgb.org.service.UserTempService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserTempService userService;
	
	
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		return "/admin/user/list";
	}
	
	
	@ResponseBody
	@GetMapping("/lists")
	public String lists(Integer offset,Integer pageSize,String search) {
		if(offset == null || pageSize ==null) { return "Parameter Error"; }
		String json = null; //返回的数据
		try {
			int total = userService.getCountService(search);
			BootStrapPager bootStrapPager = new BootStrapPager(offset, pageSize, total);
			List<UserTemp> list = userService.getListService(bootStrapPager.getPageNum(), pageSize,search);
			bootStrapPager.setRows(list);
			bootStrapPager.setSearch(search);
			json = StringUtils.removeBracket(JsonUtils.toJSONString(bootStrapPager));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") String id) {
		UserTemp user = new UserTemp("0","","","");
		try {
			user = userService.getBeanByIdService(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@GetMapping("/toUpdate")
	public String toUpdate(HttpServletRequest request,String id) {
		UserTemp user = new UserTemp("0","","","");
		try {
			if(id != null && !"0".equals(id)) {
				user = userService.getBeanByIdService(id);
			}
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/admin/user/update";
	}
	
	
	/**
	 * 保存/更新用户
	 * @param user
	 * @return
	 */
	@PostMapping("")
	public String update(UserTemp user) {
		try {
			if(user != null) {
				if(!"0".equals(user.getId())) {
					userService.updateService(user);
				}else {
					user.setId(StringUtils.getUUID());
					userService.saveService(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/list";
	}
	
	@GetMapping("/del")
	@ResponseBody
	public int del(String id) {
		int result = 0;
		try {
			if(id != null && !"0".equals(id)) {
				result = userService.deleteByIdService(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
