package com.yc.votesys.biz.impl;

import com.yc.votesys.biz.IUserInfoBiz;
import com.yc.votesys.dao.IBaseDao;
import com.yc.votesys.dao.impl.BaseDapImpl;
import com.yc.votesys.entity.UserInfo;
import com.yc.votesys.util.StringUtil;

public class UserInfoBizImpl implements IUserInfoBiz{
	@Override
	public int reg(UserInfo uf) {
		if (StringUtil.isNull(uf.getUname(), uf.getPwd())) {
			return -1;
		}
		IBaseDao baseDao = new BaseDapImpl<>();
		return baseDao.insert(UserInfo.class, uf, "reg");
	}

	@Override
	public UserInfo login(UserInfo uf) {
		if (StringUtil.isNull(uf.getUname(), uf.getPwd())) {
			return null;
		}
		IBaseDao baseDao = new BaseDapImpl<>();
		return (UserInfo) baseDao.finds(UserInfo.class, uf, "login");
	}
}
