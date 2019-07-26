package com.yc.votesys.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.yc.votesys.biz.ITopicBiz;
import com.yc.votesys.dao.IBaseDao;
import com.yc.votesys.dao.impl.BaseDapImpl;
import com.yc.votesys.entity.TopicItem;
import com.yc.votesys.entity.Topics;
import com.yc.votesys.util.StringUtil;

public class TopicBizImpl implements ITopicBiz{
	@Override
	public int add(String title, String type, String options, int usid) {
		if (StringUtil.isNull(title, type, options)) {
			return -1;
		}
		String tid = "T" + new Date().getTime();
		Topics topics = new Topics(tid, title, usid, Integer.parseInt(type), "");
		
		List<TopicItem> items = new ArrayList<TopicItem>();
		TopicItem item = null;
		String[] option = options.split("&");
		for (String str : option){
			item = new TopicItem(0, tid, str, 0);
			items.add(item);
		}
		
		IBaseDao baseDao = new BaseDapImpl<>();
		List<String> sqlIds = new ArrayList<String>();
		Collections.addAll(sqlIds, "addTopic", "addTopicItem");
		
		List<Object> objs = new ArrayList<Object>();
		Collections.addAll(objs, topics, items);
		
		return baseDao.update(Topics.class, objs, sqlIds);
	}

	@Override
	public List<Topics> findAll() {
		IBaseDao baseDao = new BaseDapImpl<>();
		return baseDao.findAll(Topics.class, "findAll");
	}

	@Override
	public Topics findByTno(String tno) {
		if (StringUtil.isNull(tno)) {
			return null;
		}
		IBaseDao baseDao = new BaseDapImpl<>();
		return (Topics) baseDao.finds(Topics.class, tno, "findByTno");
	}

	public int vote(String tid, String usids, String tnos) {
		if (StringUtil.isNull(tid, usids, tnos)) {
			return -1;
		}
		IBaseDao baseDao = new BaseDapImpl<>();
		
		List<String> sqlIds = new ArrayList<String>();
		Collections.addAll(sqlIds, "updateTopics", "updateTopicItems");
		
		List<Object> objs = new ArrayList<Object>();
		Collections.addAll(objs, new Topics(tid, "", 0, 0, usids), tnos.split(";"));
		
		return baseDao.update(Topics.class, objs, sqlIds);
	}
}
