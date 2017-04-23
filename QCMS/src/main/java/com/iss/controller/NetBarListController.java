/**
 * Copyright (R) 2017 isoftstone
 * @author: yjdai
 * @date: 2017年2月13日
 * @version: 1.0
 */
package com.iss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.entity.StatNetBarEntity;
import com.iss.service.IAreasCodeService;
import com.iss.service.INetBarListService;
import com.iss.util.JsonUtil;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

@Controller
@RequestMapping("/netbarList")
public class NetBarListController extends BaseController {
	@Autowired
	private INetBarListService iNetBarListService;
	@Autowired
	private IAreasCodeService iAreasCodeService;
	
	@RequestMapping("/list")
	public String list(Model model){
		String json = iAreasCodeService.getTreeAreas();
		model.addAttribute("areasTree", json);
		List<StatNetBarEntity> statNetBarList = iNetBarListService.loadStatNetBar();
		model.addAttribute("statNetBarList", statNetBarList);
		return "wh/netbar_list";
	}
	
	/**
	 * 加载列表数据
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load", produces="application/json;charset=UTF-8")
	public String load(DataParam param){
		DataTables<StatNetBarEntity> dt = iNetBarListService.loadStatNetBar(param);
		return JsonUtil.toJson(dt);
	}
}
