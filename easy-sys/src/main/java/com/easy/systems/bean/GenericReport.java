package com.easy.systems.bean;

import java.util.List;

public class GenericReport<T> {

	private String reportName;

	private List<T> reportContent;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public List<T> getReportContent() {
		return reportContent;
	}

	public void setReportContent(List<T> reportContent) {
		this.reportContent = reportContent;
	}

}
