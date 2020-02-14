/**
 * 
 */
package com.easy.systems.bean;

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

/**
 * @author amit
 *
 */

@Entity
@Table (name="Invoice_Number")
@XmlRootElement
public class InvoiceCounter {
	
	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Invoice_Date", nullable = false)
	private Date invoiceDate;
	
	@Column(name = "Invoice_Counter", nullable = false)
	private long invoiceCounter;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceCounter
	 */
	public long getInvoiceCounter() {
		return invoiceCounter;
	}

	/**
	 * @param invoiceCounter the invoiceCounter to set
	 */
	public void setInvoiceCounter(long invoiceCounter) {
		this.invoiceCounter = invoiceCounter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceCounter [invoiceDate=" + invoiceDate + ", invoiceCounter=" + invoiceCounter + "]";
	}
	
	
	
	

}
