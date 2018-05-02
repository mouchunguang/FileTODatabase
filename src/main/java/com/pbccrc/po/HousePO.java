package com.pbccrc.po;

import java.io.Serializable;

public class HousePO implements Serializable{
	private static final long serialVersionUID = -5354478719463937917L;

	private Long houseId;

    private Long userId;

    private String houseName;

    private String houseAddr;

    private String houseProvince;

    private String houseCity;

    private String houseCounty;

    private String housePostcode;

   
    public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr == null ? null : houseAddr.trim();
    }

    public String getHouseProvince() {
        return houseProvince;
    }

    public void setHouseProvince(String houseProvince) {
        this.houseProvince = houseProvince == null ? null : houseProvince.trim();
    }

    public String getHouseCity() {
        return houseCity;
    }

    public void setHouseCity(String houseCity) {
        this.houseCity = houseCity == null ? null : houseCity.trim();
    }

    public String getHouseCounty() {
        return houseCounty;
    }

    public void setHouseCounty(String houseCounty) {
        this.houseCounty = houseCounty == null ? null : houseCounty.trim();
    }

    public String getHousePostcode() {
        return housePostcode;
    }

    public void setHousePostcode(String housePostcode) {
        this.housePostcode = housePostcode == null ? null : housePostcode.trim();
    }

	@Override
	public String toString() {
		return "HousePO [houseId=" + houseId + ", userId=" + userId + ", houseName=" + houseName + ", houseAddr="
				+ houseAddr + ", houseProvince=" + houseProvince + ", houseCity=" + houseCity + ", houseCounty="
				+ houseCounty + ", housePostcode=" + housePostcode + "]";
	}
    
}