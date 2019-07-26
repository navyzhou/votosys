package com.yc.votesys.biz;

import com.yc.votesys.entity.UserInfo;

public interface IUserInfoBiz {
	/**
	 * 用户注册
	 * @param uf
	 * @return
	 */
	public int reg(UserInfo uf);
	
	/**
	 * 用户登录
	 * @param uf
	 * @return
	 */
	public UserInfo login(UserInfo uf);
}
