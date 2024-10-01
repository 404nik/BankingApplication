package com.mindgate.main.domain;

import java.time.LocalDate;

import lombok.Data;

//@Data
public class Customer {
    private int customerId;
    private int fileId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerMobile;
    private String customerAddress;
    private LocalDate customerDob;
    private LocalDate customerAccountCreationDate;
    private String customerPassword;
    private String customerAccountStatus;
	private int customerLoginCount;
    
    public Customer() {
    }

    public Customer(int customerId, int fileId, String customerFirstName, String customerLastName, String customerEmail, String customerMobile, String customerAddress, LocalDate customerDob, LocalDate customerAccountCreationDate, String customerPassword, String customerAccountStatus) {
        this.customerId = customerId;
        this.fileId = fileId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.customerMobile = customerMobile;
        this.customerAddress = customerAddress;
        this.customerDob = customerDob;
        this.customerAccountCreationDate = customerAccountCreationDate;
        this.customerPassword = customerPassword;
        this.customerAccountStatus = customerAccountStatus;
    }
    
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public LocalDate getCustomerDob() {
		return customerDob;
	}
	public void setCustomerDob(LocalDate customerDob) {
		this.customerDob = customerDob;
	}
	public LocalDate getCustomerAccountCreationDate() {
		return customerAccountCreationDate;
	}
	public void setCustomerAccountCreationDate(LocalDate customerAccountCreationDate) {
		this.customerAccountCreationDate = customerAccountCreationDate;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAccountStatus() {
		return customerAccountStatus;
	}
	public void setCustomerAccountStatus(String customerAccountStatus) {
		this.customerAccountStatus = customerAccountStatus;
	}
	public int getCustomerLoginCount() {
		return customerLoginCount;
	}
	public void setCustomerLoginCount(int customerLoginCount) {
		this.customerLoginCount = customerLoginCount;
	}

    
    @Override
    public String toString() {
        return "{" +
            " customerId='" + getCustomerId() + "'" +
            ", fileId='" + getFileId() + "'" +
            ", customerFirstName='" + getCustomerFirstName() + "'" +
            ", customerLastName='" + getCustomerLastName() + "'" +
            ", customerEmail='" + getCustomerEmail() + "'" +
            ", customerMobile='" + getCustomerMobile() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", customerDob='" + getCustomerDob() + "'" +
            ", customerAccountCreationDate='" + getCustomerAccountCreationDate() + "'" +
            ", customerPassword='" + getCustomerPassword() + "'" +
            ", customerAccountStatus='" + getCustomerAccountStatus() + "'" +
			", customerLoginCount='" + getCustomerLoginCount() + "'" +
			"}";

    }

}
