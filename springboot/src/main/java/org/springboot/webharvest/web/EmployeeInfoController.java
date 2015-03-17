package org.springboot.webharvest.web;


import org.springboot.webharvest.service.EmployeeInfoService;
import org.springboot.webharvest.entity.EmployeeInfo;
import org.springboot.common.BasicController;

import net.infotop.web.easyui.DataGrid;
import net.infotop.web.easyui.Message;
import ch.qos.logback.classic.Logger;

import org.springside.modules.web.Servlets;



import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * EmployeeInfoAction
 * $Id: EmployeeInfoAction.java,v 0.0.1 2015-03-07 16:09:04  $
 */
@Controller
 @RequestMapping(value = "/employeeinfo") 
public class EmployeeInfoController extends BasicController {
    
	@Autowired
    private EmployeeInfoService employeeinfoService;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
    
    @RequestMapping(value = "")
	public String list(Model model, ServletRequest request) {
    	/*ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByLoginName(su.getLoginName());
		if (user != null) {
			//TODO add some code.
		} else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			
			return "redirect:/login";
		}*/
    	
    	EmployeeInfo employeeinfo = new EmployeeInfo();
    	 model.addAttribute("employeeinfo", employeeinfo);
       return "mainFrame";
    }
    
    
    @RequestMapping(value="coverpage")
	public String coverPage(Model model) 
	{
		
		String piechart = "";
		model.addAttribute("piechart",piechart);
		return "home/coverpage";
	}
    
    @RequestMapping(value ="show")
   	public String show(Model model, ServletRequest request) {
    
       	EmployeeInfo employeeinfo = new EmployeeInfo();
       	 model.addAttribute("employeeinfo", employeeinfo);
       	 return "employeeinfo/employeeinfoList";
       }

    
    
    /**
	 * 获取列表数据
	 * @param sortType
	 * @param order
	 * @param pageNumber
	 * @param rows
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "findList")
	@ResponseBody
	public DataGrid findList(
			@RequestParam(value = "sort", defaultValue = "auto") String sortType,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = ROWS) int rows,
			Model model, ServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		try {
			/*ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByLoginName(su.getLoginName());
			if (user != null) {*/
				Map<String, Object> searchParams = Servlets
						.getParametersStartingWith(request, "search_");
				model.addAttribute("searchParams", Servlets
						.encodeParameterStringWithPrefix(searchParams, "search_"));
				dataGrid = employeeinfoService.dataGrid(searchParams, pageNumber,
						rows, sortType, order);
			/*} else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			}*/
		} catch (Exception ex) {
			//logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
		}
		return dataGrid;
	}

	/**
	 * 跳转表单
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "create", method = RequestMethod.GET)
	 public String createForm(Model model) {
	     /*ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByLoginName(su.getLoginName());
			if (user != null) {*/
				 EmployeeInfo entity = new EmployeeInfo(); 
			     model.addAttribute("employeeinfo", entity);
			     model.addAttribute("action", "create");
			/*} else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}*/
	     return "employeeinfo/employeeinfoForm";
	 }
	 
	/**
	 * 保存表单数据
	 * @return
	 */
	 @RequestMapping(value = "create", method = RequestMethod.POST)
	 @ResponseBody
	 public Message create(@Valid EmployeeInfo employeeinfo, RedirectAttributes redirectAttributes) {
		try {
			/*ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByLoginName(su.getLoginName());
			if (user != null) {*/
		    	employeeinfoService.save(employeeinfo);
				msg.setSuccess(true);
				msg.setMessage("信息添加成功");
				msg.setData(employeeinfo);
			/*} else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				msg.setSuccess(false);
				msg.setMessage("登陆帐号无效!");
				msg.setData("");
			}*/
		} catch (Exception ex) {
			//logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
			msg.setSuccess(false);
			msg.setMessage(ex.getMessage());
			msg.setData("");
		}
		return msg;
	}
	 
	 /**
	 * 跳转更新表单
	 * @param id
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "{id}", method = RequestMethod.GET)
	    public String updateForm(@PathVariable("id") Long id, Model model) {
	       /* ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByLoginName(su.getLoginName());
			if (user != null) {*/
				EmployeeInfo entity = employeeinfoService.get(id); 
		        model.addAttribute("employeeinfo", entity);
		        model.addAttribute("action", "update");
			/*} else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}*/
	        return "employeeinfo/employeeinfoForm";
	    }
	 
	 
	 /**
	 * 更新表单数据
	 * @param user
	 * @return
	 */
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 @ResponseBody
	    public Message update(@Valid @ModelAttribute("preloadEmployeeInfo") EmployeeInfo employeeinfo,
	            RedirectAttributes redirectAttributes) {
		 try {
			 	/*ShiroUser su = super.getLoginUser();
				User user = accountService.findUserByLoginName(su.getLoginName());
				if (user != null) {*/
			    	employeeinfoService.save(employeeinfo);
					msg.setSuccess(true);
					msg.setMessage("信息更新成功");
					msg.setData(employeeinfo);
				/*} else {
					logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
					msg.setSuccess(false);
					msg.setMessage("登陆帐号无效!");
					msg.setData("");
				}*/
			} catch (Exception ex) {
				//logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
				msg.setSuccess(false);
				msg.setMessage(ex.getMessage());
				msg.setData("");
			}
			return msg;
	    }
	 
	 
	 
	 /**
	 * 删除记录
	 * @param ids 
	 * @return
	 */	
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 @ResponseBody
	    public Message delete(@RequestParam(value = "ids") List<Long> ids,
	            ServletRequest request) throws Exception {
	     try {
	    	 	/*ShiroUser su = super.getLoginUser();
				User user = accountService.findUserByLoginName(su.getLoginName());
				if (user != null) {*/
					employeeinfoService.delete(ids);
					msg.setSuccess(true);
					msg.setMessage("信息删除成功");
					msg.setData("");
				/*} else {
					logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
					msg.setSuccess(false);
					msg.setMessage("登陆帐号无效!");
					msg.setData("");
				}*/
	    	 	
			} catch (Exception ex) {
				ex.printStackTrace();
				msg.setSuccess(false);
				msg.setMessage(ex.getMessage());
				msg.setData("");

			}
			return msg;
	    }
	 
	 @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	    public String view(@PathVariable("id") Long id, Model model) {
		 	/*ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByLoginName(su.getLoginName());
			if (user != null) {*/
			 	EmployeeInfo entity = employeeinfoService.get(id); 
			 	model.addAttribute("employeeinfo", entity);
			/*} else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}*/
		 	return "employeeinfo/employeeinfoView";
	    }
	 
	 
	   @ModelAttribute("preloadEmployeeInfo")
	    public EmployeeInfo getEmployeeInfo(@RequestParam(value = "id", required = false) Long id) {
	        if (id != null) {
	            return employeeinfoService.get(id);
	        }
	        return null;
	    }

}