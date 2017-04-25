/**
 * Copyright (R) 2017 isoftstone
 * @author: yjdai
 * @date: 2017年2月13日
 * @version: 1.0
 */
package com.iss.controller;

import java.util.List;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iss.entity.AreasEntity;
import com.iss.entity.NetBarEntity;
import com.iss.service.IAreasCodeService;
import com.iss.service.INetBarService;
import com.iss.util.JsonUtil;
import com.iss.vo.AjaxJson;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

/**
 * 缺陷管理
 */
@Controller
@RequestMapping("/netbar")
public class NetBarController extends BaseController {
	@Autowired
	private INetBarService iNetBarService;
	@Autowired
	private IAreasCodeService iAreasCodeService;
	
	@RequestMapping("/regList")
	public String regList(Model model){
		List<AreasEntity> areasList = iAreasCodeService.getTwolevelAreas("410000");
		model.addAttribute("areasList", areasList);
		return "wh/netbar_reg";
	}
	
	/**
	 * 加载列表数据
	 * @author yjdai 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load", produces="application/json;charset=UTF-8")
	public String load(DataParam param){
		DataTables<NetBarEntity> dt = iNetBarService.load(param);
		return JsonUtil.toJson(dt);
	}
	
	/**
	 * 添加数据
	 * @author yjdai 
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public AjaxJson add(NetBarEntity entity){
		AjaxJson json = new AjaxJson();
		NetBarEntity fixed = iNetBarService.save(entity);
		if(fixed != null){
			json.setFlag(true);
			json.setObj(fixed);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/getAreas")
	public AjaxJson getCounty(String areasid){
		AjaxJson json = new AjaxJson();
		List<AreasEntity> areasList = iAreasCodeService.getThreelevelAreas(areasid);
		if(areasList != null){
			json.setFlag(true);
			json.setObj(areasList);
		}
		return json;
	}
	
	/**
	 * 更新数据
	 * @author yjdai 
	 * @param pk
	 * @param name
	 * @param value
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/edit")
	public AjaxJson edit(Long pk, String name, String value){
		AjaxJson json = new AjaxJson();
		boolean bool = iNetBarService.update(pk, name, value);
		json.setFlag(bool);
		return json;
	}
	
	/**
	 * 批量删除数据
	 * @author yjdai 
	 * @param ids 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delBatch")
	public AjaxJson delBatch(@RequestParam("ids[]")Long[] ids){
		AjaxJson json = new AjaxJson();
		boolean bool = iNetBarService.delBatch(ids);
		json.setFlag(bool);
		return json;
	}
	
	/**
	 * 全量导出数据
	 * @author yjdai 
	 * @param model
	 * @return
	 */
	@RequestMapping("/export")
    public String export(Model model) {
		List<NetBarEntity> list = iNetBarService.load();
		String title = "缺陷数据";
        model.addAttribute(NormalExcelConstants.FILE_NAME, title);
        model.addAttribute(NormalExcelConstants.CLASS, NetBarEntity.class);
        model.addAttribute(NormalExcelConstants.PARAMS, new ExportParams(title, title));
        model.addAttribute(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }
}
