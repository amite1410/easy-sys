package com.easy.systems.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Customer_Details")
@XmlRootElement
public class CustomerDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1979724044732684763L;
	
	@Id
	@Column(name="Customer_ID",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Customer_Name", nullable=false)
	private String customerName;
	@Column(name="Firm_Name", nullable=false,unique=true)
	private String firmName;
	@Column(name="GST_Number", unique=false)
	private String gstNumber;
	@Column(name="PAN_NUMBER")
	private String panNumber;
	@Column(name="Addhar_Number")
	private String addharNumber;
	@Column(name="Route_No",nullable=false)
	private Integer route_No;
	@Column(name="Address_Line1")
	private String addressLine1;
	@Column(name="Address_Line2")
	private String addressLine2;
	@Column(name="State")
	private String state;
	@Column(name="Pin_Code")
	private String pinCode;
	@Column(name="Conatact_No1")
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAddharNumber() {
		return addharNumber;
	}
	public void setAddharNumber(String addharNumber) {
		this.addharNumber = addharNumber;
	}
	public Integer getRoute_No() {
		return route_No;
	}
	public void setRoute_No(Integer route_No) {
		this.route_No = route_No;
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

	
	
	}
