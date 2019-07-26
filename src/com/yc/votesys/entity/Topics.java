package com.yc.votesys.entity;

import java.io.Serializable;
import java.util.List;

public class Topics implements Serializable{
	private static final long serialVersionUID = 1173284219056355515L;
	private String tid;
	private String tname;
	private int usid;
	private int topicType;
	private String usids;
	
	private String uname;
	private List<TopicItem> topicItems;

	@Override
	public String toString() {
		return "Topics [tid=" + tid + ", tname=" + tname + ", usid=" + usid + ", topicType=" + topicType + ", usids="
				+ usids + ", uname=" + uname + ", topicItems=" + topicItems + "]";
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

	public String getUsids() {
		return usids;
	}

	public void setUsids(String usids) {
		this.usids = usids;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public List<TopicItem> getTopicItems() {
		return topicItems;
	}

	public void setTopicItems(List<TopicItem> topicItems) {
		this.topicItems = topicItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + topicType;
		result = prime * result + usid;
		result = prime * result + ((usids == null) ? 0 : usids.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topics other = (Topics) obj;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		if (tname == null) {
			if (other.tname != null)
				return false;
		} else if (!tname.equals(other.tname))
			return false;
		if (topicType != other.topicType)
			return false;
		if (usid != other.usid)
			return false;
		if (usids == null) {
			if (other.usids != null)
				return false;
		} else if (!usids.equals(other.usids))
			return false;
		return true;
	}

	public Topics(String tid, String tname, int usid, int topicType, String usids) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.usid = usid;
		this.topicType = topicType;
		this.usids = usids;
	}

	public Topics() {
		super();
	}
}
