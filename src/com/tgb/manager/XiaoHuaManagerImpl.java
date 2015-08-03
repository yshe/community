/**
 * This file created at 2015年7月20日.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tgb.manager;

import com.tgb.dao.XiaoHuaDao;
import com.tgb.entity.Humor;

/**
 * <code>{@link XiaoHuaManagerImpl}</code>
 *
 * TODO : document me
 *
 * @author yabushan
 */
public class XiaoHuaManagerImpl implements XiaoHuaManager {

	/* (non-Javadoc)
	 * @see com.tgb.manager.XiaoHuaManager#getOneHumor(java.lang.Integer)
	 */
	
	private XiaoHuaDao xiaoHuaDao;
	/**
	 * @param xiaoHuaDao the xiaoHuaDao to set
	 */
	public void setXiaoHuaDao(XiaoHuaDao xiaoHuaDao) {
		this.xiaoHuaDao = xiaoHuaDao;
	}
	@Override
	public Humor getOneHumor(Integer id) {
		return xiaoHuaDao.getOneHumor(id);
	}

}
