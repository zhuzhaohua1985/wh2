package com.iss.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 省市级统计表
 * @author gxie
 *
 */
public class StatNetBarEntity {
	private Long id;
	private String qhdm;//行政区划代码
	private Date rq;//日期
	private Long zxwb;//在线网吧数
	private Long lxwb;//离线网吧数
	private Long jqzs;//机器总数
	private Long yhzs;//用户总数（用户最大数）
	private Timestamp czsj;//操作日期

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQhdm() {
		return qhdm;
	}

	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Long getZxwb() {
		return zxwb;
	}

	public void setZxwb(Long zxwb) {
		this.zxwb = zxwb;
	}

	public Long getLxwb() {
		return lxwb;
	}

	public void setLxwb(Long lxwb) {
		this.lxwb = lxwb;
	}

	public Long getJqzs() {
		return jqzs;
	}

	public void setJqzs(Long jqzs) {
		this.jqzs = jqzs;
	}

	public Long getYhzs() {
		return yhzs;
	}

	public void setYhzs(Long yhzs) {
		this.yhzs = yhzs;
	}

	public Timestamp getCzsj() {
		return czsj;
	}

	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
}
