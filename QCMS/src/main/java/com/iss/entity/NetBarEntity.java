/**
 * Copyright (R) 2017 isoftstone
 * @author: yjdai
 * @date: 2017年2月13日
 * @version: 1.0
 */
package com.iss.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.handler.inter.IExcelModel;

/**
 * 网吧实体
 */
@Entity
@Table(name="t_net_bar")
public class NetBarEntity implements Serializable, IExcelModel {
	/**
	 * @Fields serialVersionUID : long
	 */
	private static final long serialVersionUID = 1L;

	private Long rowId;// 序号
	private String errorMsg;// 错误消息
	private String id;// 网吧ID
	private String net_bar_name;// 网吧名称
	private String business_reg_no;// 工商登记号
	private String address_code;// 网吧地址代码
	private String address_name;// 网吧地址名称
	private String contact_name;// 联系人姓名
	private String contact_tel;// 联系人电话
	private Integer client_total;// 实际客户机总数
	private String outside_network;// 网吧公网IP
	private String inside_network;// 服务器内网IP
	private String server_mac;// 服务器MAC
	private String creator;//创建者
	private Timestamp create_time;//创建时间

	@Transient
	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	@Override
	@Transient
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getNet_bar_name() {
		return net_bar_name;
	}

	public void setNet_bar_name(String net_bar_name) {
		this.net_bar_name = net_bar_name;
	}

	public String getBusiness_reg_no() {
		return business_reg_no;
	}

	public void setBusiness_reg_no(String business_reg_no) {
		this.business_reg_no = business_reg_no;
	}

	public String getAddress_code() {
		return address_code;
	}

	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_tel() {
		return contact_tel;
	}

	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}

	public Integer getClient_total() {
		return client_total;
	}

	public void setClient_total(Integer client_total) {
		this.client_total = client_total;
	}

	public String getOutside_network() {
		return outside_network;
	}

	public void setOutside_network(String outside_network) {
		this.outside_network = outside_network;
	}

	public String getInside_network() {
		return inside_network;
	}

	public void setInside_network(String inside_network) {
		this.inside_network = inside_network;
	}

	public String getServer_mac() {
		return server_mac;
	}

	public void setServer_mac(String server_mac) {
		this.server_mac = server_mac;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Id
	@Column(name="id", nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
