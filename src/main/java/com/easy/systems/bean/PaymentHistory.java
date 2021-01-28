package com.easy.systems.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table (name="Payment_History")
@XmlRootElement
public class PaymentHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473465572902826068L;
	public PaymentHistory(){}
	
	
	@Id
	@Column(name = "Payment_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	@Column(name = "Invoice_Code",nullable=false)
	private String invoiceCode;
	@Column(name = "Payment_Code")
	private String paymentCode;
	
	
	@Column(name = "Amount_Collected",nullable=false)
	private  BigDecimal amountCollected;
	
	@Column(name = "Invoice_Amount")
	private  BigDecimal invoiceAmount;
	
	@Column(name = "Collected_By",nullable=false)
	private  String collectedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Payment_Date", nullable = false)
	private Date paymentDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public BigDecimal getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(BigDecimal amountCollected) {
		this.amountCollected = amountCollected;
	}

	public String getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Override
	public String toString() {
		return "PaymentHistory [id=" + id + ", invoiceCode=" + invoiceCode + ", paymentCode=" + paymentCode
				+ ", amountCollected=" + amountCollected + ", collectedBy=" + collectedBy + ", paymentDate="
				+ paymentDate + "]";
	}


}
