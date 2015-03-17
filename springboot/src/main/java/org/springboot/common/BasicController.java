/**
 * @(#)BasicController.java	  2012-10-17 下午10:26:55
 * @since JDK1.6
 * 
 * 版权所有 © 1999-2011  临沂市拓普网络有限公司
 */
package org.springboot.common;


import net.infotop.web.easyui.Message;

import org.apache.shiro.SecurityUtils;
import org.springboot.common.log.BasicLogger;
import org.springboot.system.account.service.AccountService;
import org.springboot.system.account.service.ShiroDbRealm.ShiroUser;
import org.springboot.system.area.service.AreaService;
import org.springboot.system.parameter.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.mapper.JsonMapper;

//import org.springside.system.account.service.ShiroDbRealm.ShiroUser;
/**
 * <p>
 * Title:基础控制类
 * </p>
 * <p>
 * Description: 基础控制类，定义了通用的参数和方法
 * </p>
 * <p>
 * Copyright:版权所有 © 1999-2012
 * </p>
 * <p>
 * Company:临沂市拓普网络有限公司
 * </p>
 */
public abstract class BasicController {

	 @Autowired
	public AccountService accountService;

	@Autowired
	public ParameterService parameterService;

	@Autowired
	public AreaService areaService;
	
	@Autowired
	public BasicLogger logger; 

	protected JsonMapper json = new JsonMapper();

	protected Message msg = new Message();

	public static final int PAGE_SIZE = 15;

	public static final String ROWS = "15";


	 public static ShiroUser getLoginUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	} 

}
