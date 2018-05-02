package com.pbccrc.po;

import java.io.Serializable;

public class CarPO implements Serializable{
	private static final long serialVersionUID = 2277769929631994610L;

	private Long carId;

    private Long userId;

    private String carName;

    private String carBrand;

    private String carProvider;

    private String carEngine;

    private String carGearbox;

    private String carStructure;

    private Integer carQualityguarantee;

    public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    public String getCarProvider() {
        return carProvider;
    }

    public void setCarProvider(String carProvider) {
        this.carProvider = carProvider == null ? null : carProvider.trim();
    }

    public String getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(String carEngine) {
        this.carEngine = carEngine == null ? null : carEngine.trim();
    }

    public String getCarGearbox() {
        return carGearbox;
    }

    public void setCarGearbox(String carGearbox) {
        this.carGearbox = carGearbox == null ? null : carGearbox.trim();
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure == null ? null : carStructure.trim();
    }

    public Integer getCarQualityguarantee() {
        return carQualityguarantee;
    }

    public void setCarQualityguarantee(Integer carQualityguarantee) {
        this.carQualityguarantee = carQualityguarantee;
    }

	@Override
	public String toString() {
		return "CarPO [carId=" + carId + ", userId=" + userId + ", carName=" + carName + ", carBrand=" + carBrand
				+ ", carProvider=" + carProvider + ", carEngine=" + carEngine + ", carGearbox=" + carGearbox
				+ ", carStructure=" + carStructure + ", carQualityguarantee=" + carQualityguarantee + "]";
	}
}