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

@Entity(name = "Expense_Definition")
@XmlRootElement
public class ExpenseDefinition {
	
	@Id
	@Column(name="Expense_Category_ID",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Expense_Category", nullable=false)
	private String expenseCategory;
	@Column(name="Expense_Name", nullable=false,unique=true)
	private String expenseName;
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

}
