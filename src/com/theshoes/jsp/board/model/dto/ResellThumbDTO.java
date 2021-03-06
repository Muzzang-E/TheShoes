package com.theshoes.jsp.board.model.dto;

import java.io.Serializable;

public class ResellThumbDTO implements Serializable{

	private static final long serialVersionUID = 4292895152934337466L;
	
	private int rtNo;
	private int resellThumbNo;
	private String originalName;
	private String savedName;
	private String savePath;
	private String fileType;
	private String thumbnailPath;
	private String status;

	public ResellThumbDTO() {
		
	}

	public ResellThumbDTO(int rtNo, int resellThumbNo, String originalName, String savedName, String savePath,
			String fileType, String thumbnailPath, String status) {
		this.rtNo = rtNo;
		this.resellThumbNo = resellThumbNo;
		this.originalName = originalName;
		this.savedName = savedName;
		this.savePath = savePath;
		this.fileType = fileType;
		this.thumbnailPath = thumbnailPath;
		this.status = status;
	}

	public int getRtNo() {
		return rtNo;
	}

	public void setRtNo(int rtNo) {
		this.rtNo = rtNo;
	}

	public int getResellThumbNo() {
		return resellThumbNo;
	}

	public void setResellThumbNo(int resellThumbNo) {
		this.resellThumbNo = resellThumbNo;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ResellThumbDTO [rtNo=" + rtNo + ", resellThumbNo=" + resellThumbNo + ", originalName=" + originalName
				+ ", savedName=" + savedName + ", savePath=" + savePath + ", fileType=" + fileType + ", thumbnailPath="
				+ thumbnailPath + ", status=" + status + "]";
	}

	
	
}
