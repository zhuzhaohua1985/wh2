package com.iss.dao;

import com.iss.entity.NetBarEntity;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

public interface INetBarJPADao {

	/**
	 * 查询缺陷Datatables表格数据
	 * @param param
	 * @return
	 */
	DataTables<NetBarEntity> query(DataParam param);
	
	String queryMaxId(String areasid);
}
