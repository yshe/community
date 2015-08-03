/**
 * This file created at 2015年7月20日.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tgb.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.tgb.entity.Humor;

/**
 * <code>{@link XiaoHuaImpl}</code>
 *
 * TODO : document me
 *
 * @author yabushan
 */
public class XiaoHuaImpl implements XiaoHuaDao {

	/* (non-Javadoc)
	 * @see com.tgb.dao.XiaoHua#getOneHumor(java.lang.Integer)
	 */
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Humor getOneHumor(Integer id) {
		
		String hqlString="from Humor h where h.id=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hqlString);
		query.setInteger(0, id);
		return (Humor) query.uniqueResult();
		
	}

}
