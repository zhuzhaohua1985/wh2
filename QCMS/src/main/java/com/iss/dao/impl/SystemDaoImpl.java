/**
 * Copyright (R) 2017 isoftstone
 * @author: yjdai
 * @date: 2016-12-16
 * @version: 1.0
 */
package com.iss.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.iss.dao.ISystemDao;
import com.iss.entity.NodeEntity;
import com.iss.entity.RoleEntity;

/**
 * 系统管理持久化数据实现
 */
@Repository
public class SystemDaoImpl extends BaseJPADaoImpl<Object, Long> implements ISystemDao{
	@Override
	public boolean updateRoleNode(Long[] nodes, Long[] roles){
		StringBuffer data = new StringBuffer();
		for (Long role : roles) {
			for (Long node : nodes) {
				data.append(",("+ role +","+ node +")");
			}
		}
		String sql = "insert into t_role_node(roleId, nodeId) values"+data.substring(1)
				+" on duplicate key update nodeId=values(nodeId)";//重复记录只更新不插入
		return executeNative(sql.toString());
	}
	
	@Override
	public boolean deleteRoleNode(Long[] nodes, Long[] roles){
		String sql = "delete from t_role_node where roleId in :roles ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("roles", Arrays.asList(roles));
		if(nodes[0]==-1){//-1表示节点集合为空时，表示删除角色关联节点
			executeNative(sql, parameters);
			return true;
		}
		sql += "and nodeId not in :nodes";
		parameters.put("nodes", Arrays.asList(nodes));
		executeNative(sql, parameters);
		return true;
	}
	
	@Override
	public boolean updateRoleUser(Long[] roles, Long[] users){
		StringBuffer data = new StringBuffer();
		for (Long user : users) {
			for (Long role : roles) {
				data.append(",("+ role +","+ user +")");
			}
		}
		String sql = "insert into t_role_user(roleId, userId) values"+data.substring(1)
				+" on duplicate key update roleId=values(roleId)";//duplicate key update如果存在就insert反之update
		return executeNative(sql);
	}
	
	@Override
	public boolean deleteRoleUser(Long[] roles, Long[] users){
		String sql = "delete from t_role_user where userId in :users ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("users", Arrays.asList(users));
		if(roles[0]==-1){//-1表示角色集合为空时，表示删除用户关联角色
			executeNative(sql, parameters);
			return true;
		}
		sql += "and roleId not in :roles";
		parameters.put("roles", Arrays.asList(roles));
		executeNative(sql, parameters);
		return true;
	}
	
	@Override
	public List<RoleEntity> queryRolesForUser(Long userId){
		String sql = "SELECT r.id, r.name, r.role FROM t_role_user ru LEFT JOIN t_sys_role r ON r.id=ru.roleId "+
					"WHERE r.status=1 AND ru.userId=:userId";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		List<Object> list = findNativeQuery(sql, parameters);
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		for (Object object : list) {
			if(object instanceof Object[]){
				Object[] obj = (Object[]) object;
				RoleEntity role = new RoleEntity();
				role.setId(Long.valueOf(Objects.toString(obj[0], "0")));
				role.setName(Objects.toString(obj[1], ""));
				role.setRole(Objects.toString(obj[2], ""));
				roles.add(role);
			}
		}
		return roles;
	}
	
	@Override
	public List<NodeEntity> queryNodesForRoles(List<Long> roles, boolean isMenu, boolean isWechat){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT n.id, n.name, n.level, n.parentId, n.alias, n.sort, n.url, n.icon ")
			.append("FROM t_role_node rn LEFT JOIN t_sys_node n ON n.id=rn.nodeId ")
			.append("WHERE n.status=1 AND rn.roleId in :roles ")
			.append(isMenu?"AND n.type=1":"").append(isWechat?"AND wxNode=1":"")
			.append(" order by n.sort");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("roles", roles);
		List<Object> list = findNativeQuery(sql.toString(), parameters);
		List<NodeEntity> nodes = new ArrayList<NodeEntity>();
		for (Object object : list) {
			if(object instanceof Object[]){
				Object[] obj = (Object[]) object;
				NodeEntity node = new NodeEntity();
				node.setId(Long.valueOf(Objects.toString(obj[0], "0")));
				node.setName(Objects.toString(obj[1], ""));
				node.setLevel(Integer.valueOf(Objects.toString(obj[2], "0")));
				node.setParentId(Long.valueOf(Objects.toString(obj[3], "0")));
				node.setAlias(Objects.toString(obj[4], ""));
				node.setSort(Integer.valueOf(Objects.toString(obj[5], "0")));
				node.setUrl(Objects.toString(obj[6], ""));
				node.setIcon(Objects.toString(obj[7], ""));
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	@Override
	public boolean updateUserGroup(Long[] users, Long groupId){
		Map<String, Object> parameters = new HashMap<String, Object>();
		String sql = "update t_sys_user set groupId=:groupId where id in :users";
		parameters.put("groupId", groupId);
		parameters.put("users", Arrays.asList(users));
		executeNative(sql, parameters);
		return true;
	}
}
