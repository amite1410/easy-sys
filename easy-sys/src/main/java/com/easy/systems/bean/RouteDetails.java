package com.easy.systems.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Route_Details")
@XmlRootElement
public class RouteDetails {
	
	public RouteDetails(){}
	
	
	@Id
	@Column(name="Route_ID",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Area_Covered")
	private String areaCovered;	
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
	public String getAreaCovered() {
		return areaCovered;
	}
	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
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
	@Override
	public String toString() {
		return "RouteDetails [id=" + id + ", areaCovered=" + areaCovered + ", isActive=" + isActive + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", deactivateDate=" + deactivateDate + "]";
	}

}
