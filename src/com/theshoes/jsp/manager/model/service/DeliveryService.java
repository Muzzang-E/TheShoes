package com.theshoes.jsp.manager.model.service;

import static com.theshoes.jsp.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.theshoes.jsp.common.paging.SelectCriteria;
import com.theshoes.jsp.manager.model.dao.ManagerDAO;
import com.theshoes.jsp.manager.model.dto.DeliveryDTO;

public class DeliveryService {

	private final ManagerDAO mapper;
	
	public DeliveryService() {
		mapper = new ManagerDAO();
	}

	public int selectdeliveryTotalCount() {
		SqlSession session = getSqlSession();
		
		int totalCount = mapper.selectDeliveryTotalCount(session);
		
		session.close();
		
		return totalCount;
	}

	public List<DeliveryDTO> selectAllDeliveryList(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();
		
		List<DeliveryDTO> deliveryList = mapper.selectAllDeliveryList(session, selectCriteria);
		System.out.println("deliveryList : " + deliveryList);
		
		session.close();
		
		return deliveryList;
	}
}
