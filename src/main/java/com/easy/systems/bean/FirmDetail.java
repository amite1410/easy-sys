package com.easy.systems.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class FirmDetail implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -940437887291674961L;
	@Id
	@Column(name="Firm_ID",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Firm_Name",nullable=false)
	private String firmName;
	@Column(name="Address_Line1")
	private String addressLine1;
	@Column(name="Address_Line2")
	private String addressLine2;
	@Column(name="State")
	private String state;
	@Column(name="Pin_Code")
	private String pinCode;
	@Column(name="Conatact_No1",nullable=false)
	private String conatactNo1;
	@Column(name="Conatact_No2")
	private String conatactNo2;
	@Column(name="email")
	private String email;
	@Column(name="Is_Active")
	private String isActive;
	@Temporal(TemporalType.DATE)
	@Column(name="Create_Date",nullable=false)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name="Update_Date")
	private Date updateDate;
	@Temporal(TemporalType.DATE)
	@Column(name="Deactivate_Date")
	private Date deactivateDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getConatactNo1() {
		return conatactNo1;
	}
	public void setConatactNo1(String conatactNo1) {
		this.conatactNo1 = conatactNo1;
	}
	public String getConatactNo2() {
		return conatactNo2;
	}
	public void setConatactNo2(String conatactNo2) {
		this.conatactNo2 = conatactNo2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeactivateDate() {
		return deactivateDate;
	}
	public void setDeactivateDate(Date deactivateDate) {
		this.deactivateDate = deactivateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
