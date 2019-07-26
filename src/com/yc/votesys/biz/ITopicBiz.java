package com.yc.votesys.biz;

import java.util.List;

import com.yc.votesys.entity.Topics;

public interface ITopicBiz {
	/**
	 * 添加投票主题
	 * @param title
	 * @param type
	 * @param options
	 * @param usid
	 * @return
	 */
	public int add(String title, String type, String options, int usid);
	
	public List<Topics> findAll();
	
	public Topics findByTno(String tno);
	
	public int vote(String tid, String usids, String tnos);
}
