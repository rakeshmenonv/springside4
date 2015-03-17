package org.springboot.system;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springboot.common.BasicController;
import org.springboot.system.account.entity.User;
import org.springboot.system.account.service.ShiroDbRealm.ShiroUser;
//import org.springboot.webharvest.dataharvest.service.DataHarvestService;

@Controller
@RequestMapping(value = "/home")
public class MainController extends BasicController {

//	@Autowired
//	private DataHarvestService dataHarvestService;
	/**
	 * 首页系统选择页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainframe() {
		return "mainFrame";
	}
	
	
	@RequestMapping(value="coverpage")
	public String coverPage(Model model) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
//		String piechart = mapper.writeValueAsString(dataHarvestService.getPiechartData());
//		model.addAttribute("piechart",piechart);
		return "home/coverpage";
	}
	
	
}
