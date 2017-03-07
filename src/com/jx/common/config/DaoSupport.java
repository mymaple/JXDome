package com.jx.common.config;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jx.common.config.exception.UncheckedException;

@Repository("daoSupport")
public class DaoSupport implements DAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 新增对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object add(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}

	/**
	 * 批量新增
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object batchAdd(String str, List objs) throws Exception {
		return sqlSessionTemplate.insert(str, objs);
	}
	
	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int delete(String str, Object obj) throws Exception, UncheckedException {
		int deleteCount = sqlSessionTemplate.delete(str, obj);
		if(deleteCount <= 0){
			throw new UncheckedException("40002", null, "删除失败");
		}
		return deleteCount;
	}
	
	/**
	 * 批量删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int batchDelete(String str, List objs) throws Exception {
		return sqlSessionTemplate.delete(str, objs);
	}

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int edit(String str, Object obj) throws Exception, UncheckedException {
		int updateCount = sqlSessionTemplate.update(str, obj);
		if(updateCount <= 0){
			throw new UncheckedException("40001", null, "更新失败");
		}
		return updateCount;
	}

	/**
	 * 批量修改
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int batchEdit(String str, List objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		int num = 0;
		// 批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					num += sqlSession.update(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
		return num;
	}

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}

	/**
	 * 查找对象封装成List
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
		return sqlSessionTemplate.selectMap(str, obj, key);
	}

}
