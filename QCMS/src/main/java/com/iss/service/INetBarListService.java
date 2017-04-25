package com.iss.service;

import java.util.List;

import com.iss.entity.StatClientEntity;
import com.iss.entity.StatNetBarEntity;
import com.iss.vo.DataParam;
import com.iss.vo.DataTables;

public interface INetBarListService {

	List<StatNetBarEntity> loadStatNetBar();
	
	List<StatClientEntity> loadStatClient();
	
	DataTables<StatNetBarEntity> loadStatNetBar(DataParam param);
	
	DataTables<StatClientEntity> loadStatClient(DataParam param);
}
