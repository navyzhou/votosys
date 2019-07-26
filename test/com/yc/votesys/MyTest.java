package com.yc.votesys;

import org.junit.Test;

import com.yc.votesys.dao.IBaseDao;
import com.yc.votesys.dao.impl.BaseDapImpl;
import com.yc.votesys.entity.UserInfo;

public class MyTest {
	@Test
	public <T> void test1() {
		IBaseDao<T> dao = new BaseDapImpl<>();
		int result = dao.insert(UserInfo.class, new UserInfo(0, "源辰", "123"), "reg");
		System.out.println(result);
	}
}
