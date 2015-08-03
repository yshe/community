/**
 * This file created at 2015年7月20日.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tgb.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgb.entity.Humor;
import com.tgb.manager.XiaoHuaManager;

/**
 * <code>{@link XiaoHuaController}</code>
 *
 * TODO : document me
 *
 * @author yabushan
 */
@RestController
public class XiaoHuaController {

	@Resource(name="xiaoHuaManager")
	private XiaoHuaManager xiaoHuaManager;
	
	@RequestMapping(value="req/humor",method=RequestMethod.POST)
	public Humor returnoHumor(HttpServletRequest request,HttpServletResponse response){
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		Humor humor=xiaoHuaManager.getOneHumor(id);
		return humor;
	}

}
