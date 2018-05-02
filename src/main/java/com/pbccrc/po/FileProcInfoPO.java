package com.pbccrc.po;

import java.io.Serializable;

public class FileProcInfoPO implements Serializable{
	private static final long serialVersionUID = -2563195072416064791L;
	
	private String fileProcID;
	private String fileName;
	private String typeID;
	private String origFileName;
	private String procStatus;
	private String exceptionInfo;
	private String remark;
	public String getFileProcID() {
		return fileProcID;
	}
	public void setFileProcID(String fileProcID) {
		this.fileProcID = fileProcID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getOrigFileName() {
		return origFileName;
	}
	public void setOrigFileName(String origFileName) {
		this.origFileName = origFileName;
	}
	public String getProcStatus() {
		return procStatus;
	}
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
