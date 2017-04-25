package com.iss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iss.entity.StatClientEntity;
import com.iss.entity.StatNetBarEntity;
import com.iss.service.INetBarListService;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

@Service
public class NetBarListServiceImpl implements INetBarListService {

	@Override
	public List<StatNetBarEntity> loadStatNetBar() {
		List<StatNetBarEntity> data = new ArrayList<StatNetBarEntity>();
		for (int i = 0; i < 10; i++) {
			StatNetBarEntity entity = new StatNetBarEntity();
			entity.setId(1L);
			entity.setJqzs(100L);
			entity.setLxwb(100L);
			entity.setYhzs(100L);
			entity.setZxwb(100L);
			data.add(entity);
		}
		return data;
	}

	@Override
	public List<StatClientEntity> loadStatClient() {
		List<StatClientEntity> data = new ArrayList<StatClientEntity>();
		for (int i = 0; i < 10; i++) {
			StatClientEntity entity = new StatClientEntity();
			entity.setId(1L);
			entity.setZxzds(1000L);
			entity.setLxzds(1000L);
			entity.setYxzds(1000L);
			entity.setFwdbb(String.valueOf(i));
			data.add(entity);
		}
		return data;
	}

	@Override
	public DataTables<StatNetBarEntity> loadStatNetBar(DataParam param) {
		List<StatNetBarEntity> data = new ArrayList<StatNetBarEntity>();
		int total = 10;
		for (int i = 0; i < 10; i++) {
			StatNetBarEntity entity = new StatNetBarEntity();
			entity.setId(1L);
			entity.setJqzs(100L);
			entity.setLxwb(100L);
			entity.setYhzs(100L);
			entity.setZxwb(100L);
			data.add(entity);
		}
		return new DataTables<StatNetBarEntity>(param.getDraw(), total, total, data);
	}

	@Override
	public DataTables<StatClientEntity> loadStatClient(DataParam param) {
		List<StatClientEntity> data = new ArrayList<StatClientEntity>();
		int total = 10;
		for (int i = 0; i < 10; i++) {
			StatClientEntity entity = new StatClientEntity();
			entity.setId(1L);
			entity.setZxzds(1000L);
			entity.setLxzds(1000L);
			entity.setYxzds(1000L);
			entity.setFwdbb(String.valueOf(i));
			data.add(entity);
		}
		return new DataTables<StatClientEntity>(param.getDraw(), total, total, data);
	}
}