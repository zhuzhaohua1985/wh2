package com.iss.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 县网吧表
 * @author gxie
 *
 */
public class StatClientEntity {
	private Long id;
	private String wbzch;//网吧注册号
	private Date rq;//日期
	private String sfsgx;//是否上线过（true/false）
	private Long zxzds;//在线终端数
	private Long lxzds;//离线终端数
	private Long yxzds;//有效终端数（与文化客户端保持30分钟在线）
	private String fwdbb;//服务端版本号
	private Timestamp czsj;//操作时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWbzch() {
		return wbzch;
	}

	public void setWbzch(String wbzch) {
		this.wbzch = wbzch;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public String getSfsgx() {
		return sfsgx;
	}

	public void setSfsgx(String sfsgx) {
		this.sfsgx = sfsgx;
	}

	public Long getZxzds() {
		return zxzds;
	}

	public void setZxzds(Long zxzds) {
		this.zxzds = zxzds;
	}

	public Long getLxzds() {
		return lxzds;
	}

	public void setLxzds(Long lxzds) {
		this.lxzds = lxzds;
	}

	public Long getYxzds() {
		return yxzds;
	}

	public void setYxzds(Long yxzds) {
		this.yxzds = yxzds;
	}

	public String getFwdbb() {
		return fwdbb;
	}

	public void setFwdbb(String fwdbb) {
		this.fwdbb = fwdbb;
	}

	public Timestamp getCzsj() {
		return czsj;
	}

	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}

}
