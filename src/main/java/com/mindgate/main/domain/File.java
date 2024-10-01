package com.mindgate.main.domain;

public class File {
	private int fileId;
    private byte[] customerAadharCard;
    private byte[] customerPanCard;
    private byte[] customerPhoto;
    private byte[] customerSignature;

    public File() {
    }

    public File(int fileId, byte[] customerAadharCard, byte[] customerPanCard, byte[] customerPhoto, byte[] customerSignature) {
        this.fileId = fileId;
        this.customerAadharCard = customerAadharCard;
        this.customerPanCard = customerPanCard;
        this.customerPhoto = customerPhoto;
        this.customerSignature = customerSignature;
    }

    public int getFileId() {
        return this.fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public byte[] getCustomerAadharCard() {
        return this.customerAadharCard;
    }

    public void setCustomerAadharCard(byte[] customerAadharCard) {
        this.customerAadharCard = customerAadharCard;
    }

    public byte[] getCustomerPanCard() {
        return this.customerPanCard;
    }

    public void setCustomerPanCard(byte[] customerPanCard) {
        this.customerPanCard = customerPanCard;
    }

    public byte[] getCustomerPhoto() {
        return this.customerPhoto;
    }

    public void setCustomerPhoto(byte[] customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public byte[] getCustomerSignature() {
        return this.customerSignature;
    }

    public void setCustomerSignature(byte[] customerSignature) {
        this.customerSignature = customerSignature;
    }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " fileId='" + getFileId() + "'" +
    //         ", customerAadharCard='" + getCustomerAadharCard() + "'" +
    //         ", customerPanCard='" + getCustomerPanCard() + "'" +
    //         ", customerPhoto='" + getCustomerPhoto() + "'" +
    //         ", customerSignature='" + getCustomerSignature() + "'" +
    //         "}";
    // }
}
