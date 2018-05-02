package com.pbccrc.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserPO implements Serializable{
	private static final long serialVersionUID = -4868852952202316176L;

	private Long userId;

    private String userName;

    private String userSex;

    private Integer userAge;

    private String userPosition;

    private String userIntro;

    private String userBrithday;

    private String userNationality;

    private String userHousehold;

    private BigDecimal userHeight;

    private BigDecimal userWeight;

    private String userUnit;
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition == null ? null : userPosition.trim();
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro == null ? null : userIntro.trim();
    }

    public String getUserBrithday() {
        return userBrithday;
    }

    public void setUserBrithday(String userBrithday) {
        this.userBrithday = userBrithday == null ? null : userBrithday.trim();
    }

    public String getUserNationality() {
        return userNationality;
    }

    public void setUserNationality(String userNationality) {
        this.userNationality = userNationality == null ? null : userNationality.trim();
    }

    public String getUserHousehold() {
        return userHousehold;
    }

    public void setUserHousehold(String userHousehold) {
        this.userHousehold = userHousehold == null ? null : userHousehold.trim();
    }

    public BigDecimal getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(BigDecimal userHeight) {
        this.userHeight = userHeight;
    }

    public BigDecimal getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(BigDecimal userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit == null ? null : userUnit.trim();
    }

	@Override
	public String toString() {
		return "UserPO [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", userAge=" + userAge
				+ ", userPosition=" + userPosition + ", userIntro=" + userIntro + ", userBrithday=" + userBrithday
				+ ", userNationality=" + userNationality + ", userHousehold=" + userHousehold + ", userHeight="
				+ userHeight + ", userWeight=" + userWeight + ", userUnit=" + userUnit + "]";
	}
    
    
}