package com.yc.votesys.entity;

import java.io.Serializable;

public class TopicItem implements Serializable {
	private static final long serialVersionUID = 8323585208114406032L;
	private int tno;
	private String tid;
	private String iname;
	private int nums;
	
	@Override
	public String toString() {
		return "TopicItem [tno=" + tno + ", tid=" + tid + ", iname=" + iname + ", nums=" + nums + "]";
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public TopicItem(int tno, String tid, String iname, int nums) {
		super();
		this.tno = tno;
		this.tid = tid;
		this.iname = iname;
		this.nums = nums;
	}

	public TopicItem() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iname == null) ? 0 : iname.hashCode());
		result = prime * result + nums;
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + tno;
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
		TopicItem other = (TopicItem) obj;
		if (iname == null) {
			if (other.iname != null)
				return false;
		} else if (!iname.equals(other.iname))
			return false;
		if (nums != other.nums)
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		if (tno != other.tno)
			return false;
		return true;
	}
}
