package com.iss.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.iss.dao.INetBarJPADao;
import com.iss.entity.NetBarEntity;
import com.iss.util.DateUtil;
import com.iss.util.NumberUtil;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

@Repository
public class NetBarDaoImpl extends BaseJPADaoImpl<Object, Long> implements INetBarJPADao{
	@Override
	public DataTables<NetBarEntity> query(DataParam param) {
		int total=0;
		List<NetBarEntity> data = new ArrayList<NetBarEntity>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,net_bar_name,business_reg_no,address_name,contact_name,contact_tel,client_total,inside_network,server_mac,creator,create_time ")
		.append("FROM t_net_bar ");
		StringBuffer where = new StringBuffer();
		Map<String, String> search = param.getSearch();
		if(search!=null){//搜索条件
			String value = search.get("value");
			if(StringUtils.isNotBlank(value)){
				where.append("WHERE CONCAT_WS(id,net_bar_name,business_reg_no,address_name,contact_name,contact_tel,client_total,inside_network,server_mac,creator,create_time) like :search");
				parameters.put("search", ("%"+value+"%"));
			}
		}
		sql.append(where);
		//记录总数
		total = findNativeCount(sql.toString(), parameters);//先统计总数，不需要orderby和limit
		sql.append("ORDER BY ");
		if(param.getOrder()==null){
			sql.append("create_time desc, id");
		}else{
			Map<String, String> order = param.getOrder().get(0);
			int index = Integer.valueOf(order.get("column"));
			if(index < 2)
				sql.append("create_time desc, id");
			else
				sql.append(index+1).append(" "+order.get("dir"));
		}
		if(param.getLength()!=-1){//排除全部
			sql.append(" LIMIT :start, :length");
			parameters.put("start", param.getStart());
			parameters.put("length", param.getLength());
		}//row_number序号列
		//sql.insert(0, "SELECT @row_number\\:=CONVERT(@row_number +1, SIGNED) AS num, tb.* FROM(");
		//sql.append(") AS tb, (SELECT @row_number\\:=:start) AS t");//添加伪列必须带别名
		List<Object> list = findNativeQuery(sql.toString(), parameters);
		for (Object object : list) {
			if(object instanceof Object[]){
				Object[] obj = (Object[]) object;
				NetBarEntity entity = new NetBarEntity();
				entity.setId(Objects.toString(obj[0], ""));
				entity.setNet_bar_name(Objects.toString(obj[1], ""));
				entity.setBusiness_reg_no(Objects.toString(obj[2], ""));
				entity.setAddress_name(Objects.toString(obj[3], ""));
				entity.setContact_name(Objects.toString(obj[4], ""));
				entity.setContact_tel(Objects.toString(obj[5], ""));
				entity.setClient_total(NumberUtil.toInteger(obj[6]));
				entity.setInside_network(Objects.toString(obj[7], ""));
				entity.setServer_mac(Objects.toString(obj[8], ""));
				entity.setCreator(Objects.toString(obj[9], ""));
				entity.setCreate_time(DateUtil.toTimestamp(obj[10]));
				data.add(entity);
			}
		}
		return new DataTables<NetBarEntity>(param.getDraw(), total, total, data);
	}

	@Override
	public String queryMaxId(String areasid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select id from t_net_bar ");
		sql.append("where id like :search ");
		sql.append("order by id desc");
		parameters.put("search", (areasid+"%"));
		List<Object> list = findNativeQuery(sql.toString(), parameters);
		if(list == null || list.size() <= 0){
			return null;
		}
		return Objects.toString(list.get(0), "");
	}
}