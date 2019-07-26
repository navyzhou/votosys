package com.yc.votesys.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.votesys.dao.IBaseDao;

public class BaseDapImpl<T> implements IBaseDao<T>{
	private static InputStream is;
	private static SqlSessionFactory factory;
	private final String MAPPERLOCATION = "com.yc.votesys.entity.mapper.";
	
	static {
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 关闭sql会话的方法
	 * @param session
	 */
	private void close(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}
	
	/**
	 * 获取最终的sqlId路径
	 * @param c
	 * @param sqlId
	 * @return
	 */
	private String getMapperId(Class<?> c, String sqlId) {
		return MAPPERLOCATION  + c.getSimpleName() + "Mapper." + sqlId;
	}
	
	@Override
	public List<T> findAll(Class<?> c, String sqlId) {
		SqlSession session = null;
		List<T> list = null;
		try {
			session = factory.openSession();
			list = session.selectList(this.getMapperId(c, sqlId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		
		return list;
	}

	@Override
	public List<T> findAll(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		List<T> list = null;
		try {
			session = factory.openSession();
			list = session.selectList(this.getMapperId(c, sqlId), obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		
		return list;
	}

	@Override
	public T finds(Class<?> c, String sqlId) {
		SqlSession session = null;
		T t = null;
		try {
			session = factory.openSession();
			t = session.selectOne(this.getMapperId(c, sqlId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return t;
	}

	@Override
	public T finds(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		T t = null;
		try {
			session = factory.openSession();
			t = session.selectOne(this.getMapperId(c, sqlId), obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return t;
	}

	@Override
	public Double find(Class<?> c, String sqlId) {
		SqlSession session = null;
		Double dl = null;
		try {
			session = factory.openSession();
			dl = session.selectOne(this.getMapperId(c, sqlId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return dl;
	}

	@Override
	public Double find(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		Double dl = null;
		try {
			session = factory.openSession();
			dl = session.selectOne(this.getMapperId(c, sqlId), obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return dl;
	}

	@Override
	public Integer insert(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		int result = -1;
		try {
			session = factory.openSession();
			result = session.insert(this.getMapperId(c, sqlId), obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return result;
	}

	@Override
	public Integer update(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		int result = -1;
		try {
			session = factory.openSession();
			result = session.update(this.getMapperId(c, sqlId), obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return result;
	}

	@Override
	public Integer delete(Class<?> c, Object obj, String sqlId) {
		SqlSession session = null;
		int result = -1;
		try {
			session = factory.openSession();
			result = session.delete(this.getMapperId(c, sqlId), obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return result;
	}

	@Override
	public Integer update(Class<?> c, List<Object> objs, List<String> sqlIds) {
		SqlSession session = null;
		int result = -1;
		try {
			session = factory.openSession();
			for(int i = 0, len = sqlIds.size(); i < len; ++i) {
				session.update(this.getMapperId(c, sqlIds.get(i)), objs.get(i));
			}
			result = 1;
			session.commit();
		} catch (Exception e) {
			result = 0;
			session.rollback();
			e.printStackTrace();
		} finally {
			this.close(session);
		}
		return result;
	}

}
