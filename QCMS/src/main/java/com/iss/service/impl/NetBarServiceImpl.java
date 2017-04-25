package com.iss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iss.dao.ICommonDao;
import com.iss.dao.INetBarDao;
import com.iss.dao.INetBarJPADao;
import com.iss.entity.NetBarEntity;
import com.iss.service.INetBarService;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

@Service
public class NetBarServiceImpl implements INetBarService {
	@Autowired
	private INetBarDao iNetBarDao;
	@Autowired
	private ICommonDao iCommonDao;
	@Autowired
	private INetBarJPADao iNetBarJPADao;
	
	@Override
	public List<NetBarEntity> load(){
		DataParam param = new DataParam();
		param.setLength(-1);
		param.setDraw(1);
		param.setStart(0);
		return iNetBarJPADao.query(param).getData();
	}
	
	@Override
	public DataTables<NetBarEntity> load(DataParam param){
		return iNetBarJPADao.query(param);
	}
	
	@Override
	@Transactional
	public NetBarEntity save(NetBarEntity entity){
		String addressCode = entity.getAddress_code().substring(7);
		String maxId = iNetBarJPADao.queryMaxId(addressCode);
		String id = addressCode + "0001";
		if(maxId != null){
			id = String.valueOf(Long.parseLong(maxId) + 1);
		}
		entity.setId(id);
		return iNetBarDao.saveAndFlush(entity);
	}
	
	@Override
	@Transactional
	public boolean update(Long id, String field, String fieldValue) {
		return iCommonDao.updateField(id, field, fieldValue, "t_net_bar");
	}
	
	@Override
	@Transactional
	public boolean delBatch(Long[] ids) {
		return iCommonDao.delBatch("t_net_bar", ids);
	}
	
	@Override
	@Transactional
	public List<NetBarEntity> save(List<NetBarEntity> list){
		return iNetBarDao.save(list);
	}
}