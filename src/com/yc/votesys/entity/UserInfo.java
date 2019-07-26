package com.yc.votesys.entity;

import java.io.Serializable;

/**
 * 用户信息
 * @author navy
 */
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 353770254618943075L;
	private int usid;
	private String uname;
	private String pwd;
	
	@Override
	public String toString() {
		return "UserInfo [usid=" + usid + ", uname=" + uname + ", pwd=" + pwd + "]";
	}

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + usid;
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
		UserInfo other = (UserInfo) obj;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (usid != other.usid)
			return false;
		return true;
	}

	public UserInfo(int usid, String uname, String pwd) {
		super();
		this.usid = usid;
		this.uname = uname;
		this.pwd = pwd;
	}

	public UserInfo() {
		super();
	}
}
