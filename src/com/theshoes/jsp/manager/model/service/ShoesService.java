package com.theshoes.jsp.manager.model.service;

import static com.theshoes.jsp.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.theshoes.jsp.common.paging.SelectCriteria;
import com.theshoes.jsp.manager.model.dao.ManagerDAO;
import com.theshoes.jsp.shoes.model.dto.ShoesDTO;
import com.theshoes.jsp.shoes.model.dto.ShoesThumbDTO;

public class ShoesService {
	
	private final ManagerDAO mapper;
	
	public ShoesService() {
		mapper = new ManagerDAO();
	}

	public int insertShoes(ShoesDTO shoes) {
		
		/* Connection 생성 */
		SqlSession session = getSqlSession();
		
		/* 최종적으로 반환할 result 선언 */
		int result = 0;
		
		/* 먼저 board 테이블부터 thumbnail 내용부터 insert 한다. */
		int shoesResult = mapper.insertShoes(session, shoes);
		
		System.out.println("shoesResult : " + shoesResult);
		
		List<ShoesThumbDTO> fileList = shoes.getThumbList();
		
		for (int i = 0; i < fileList.size(); i++) {
			fileList.get(i).setStNo(shoes.getShoesNo());
		}
		
		int shoesThumbResult = 0;
		for (int i = 0; i < fileList.size(); i++) {
			shoesThumbResult += mapper.insertShoesThumb(session, fileList.get(i));
		}
		
		if (shoesResult > 0 && shoesThumbResult == fileList.size()) {
			session.commit();
			result = 1;
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public List<ShoesDTO> selectShoesList() {				// 좀이따 주석
		SqlSession session = getSqlSession();
		
		List<ShoesDTO> shoesList = mapper.selectShoesList(session);
		
		session.close();
		
		return shoesList;
	}

	public ShoesDTO selectShoesDetail(int shoesNo) {				
		SqlSession session = getSqlSession();
		ShoesDTO shoesDetail = null;
		
		shoesDetail = mapper.selectShoesDetail(session, shoesNo);
		
		if (shoesDetail != null) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return shoesDetail;
	}

	public int updateShoes(ShoesDTO shoes) {
		/* Connection 생성 */
		SqlSession session = getSqlSession();
		
		/* 최종적으로 반환할 result 선언 */
		int result = 0;
		
		/* 먼저 shoes 테이블부터 신발 정보를 insert 한다. */
		int shoesResult = mapper.updateShoes(session, shoes);
		
		System.out.println("shoesResult : " + shoesResult);
		
		/* 신발 썸네일 리스트를 불러온다. */
		List<ShoesThumbDTO> fileList = shoes.getThumbList();
		
		/* fileList에 shoesNo값을 넣는다. */
		for (int i = 0; i < fileList.size(); i++) {
			fileList.get(i).setStNo(shoes.getShoesNo());
		}
		
		for (ShoesThumbDTO file : fileList) {
			System.out.println("여기는 서비스 : " + file);
		}
		
//		List<ShoesThumbDTO> tmpList = mapper.selectShoesThumbNo(session, shoes.getShoesNo());
		
//		int deleteResult = mapper.deleteShoesThumb(session, shoes.getShoesNo());
		/* 신발 썸네일 테이블에 list size만큼 update 한다. */
		int shoesThumbResult = 0;
		for (int i = 0; i < fileList.size(); i++) {
//			fileList.get(i).setStNo(tmpList.get(i).getStNo());
//			fileList.get(i).setShoesThumbNo(tmpList.get(i).getShoesThumbNo());
			shoesThumbResult += mapper.updateShoesThumb(session, fileList.get(i));
			
//			shoesThumbResult += mapper.insertShoesThumb(session, fileList.get(i));
		}
		
		if (shoesResult > 0 && shoesThumbResult == fileList.size()) {
			session.commit();
			result = 1;
		} else {
			session.rollback();
		}
		for (ShoesThumbDTO file : fileList) {
			System.out.println("여기는 서비스 아래 file : " + file);
		}
		System.out.println("여기는 서비스 아래 result : " + result);
		
		session.close();
		
		return result;
	}

	public List<ShoesDTO> selectAllShoesList(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();
		
		List<ShoesDTO> shoesList = mapper.selectAllShoesList(session, selectCriteria);
		
		session.close();
		
		return shoesList;
	}

	public int selectShoesTotalCount() {
		SqlSession session = getSqlSession();
		
		int totalCount = mapper.selectShoesTotalCount(session);
		
		session.close();
		
		return totalCount;
	}

}
