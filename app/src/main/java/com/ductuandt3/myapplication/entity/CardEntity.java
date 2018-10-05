package com.ductuandt3.myapplication.entity;

public class CardEntity {
    private int mID;
    private String mCardNumber;
    private String mExpirationMonth;
    private String mExpirationYear;
    private String mCVVCard;
    private String mPostalCode;
    private String mContryCode;
    private String mPhoneNumber;
    private String mDate;

    public CardEntity() {
    }

//    public CardEntity(int mID, String mCardNumber, String mExpirationMonth, String mExpirationYear, String mCVVCard, String mPostalCode, String mContryCode, String mPhoneNumber) {
//        this.mID = mID;
//        this.mCardNumber = mCardNumber;
//        this.mExpirationMonth = mExpirationMonth;
//        this.mExpirationYear = mExpirationYear;
//        this.mCVVCard = mCVVCard;
//        this.mPostalCode = mPostalCode;
//        this.mContryCode = mContryCode;
//        this.mPhoneNumber = mPhoneNumber;
//    }

    public CardEntity(String mCardNumber, String mExpirationMonth, String mExpirationYear, String mCVVCard, String mPostalCode, String mContryCode, String mPhoneNumber, String mDate) {
        this.mCardNumber = mCardNumber;
        this.mExpirationMonth = mExpirationMonth;
        this.mExpirationYear = mExpirationYear;
        this.mCVVCard = mCVVCard;
        this.mPostalCode = mPostalCode;
        this.mContryCode = mContryCode;
        this.mPhoneNumber = mPhoneNumber;
        this.mDate = mDate;
    }

//    public CardEntity(String mCardNumber, String mDate) {
//        this.mCardNumber = mCardNumber;
//        this.mDate = mDate;
//    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmCardNumber() {
        return mCardNumber;
    }

    public void setmCardNumber(String mCardNumber) {
        this.mCardNumber = mCardNumber;
    }

    public String getmExpirationMonth() {
        return mExpirationMonth;
    }

    public void setmExpirationMonth(String mExpirationMonth) {
        this.mExpirationMonth = mExpirationMonth;
    }

    public String getmExpirationYear() {
        return mExpirationYear;
    }

    public void setmExpirationYear(String mExpirationYear) {
        this.mExpirationYear = mExpirationYear;
    }

    public String getmCVVCard() {
        return mCVVCard;
    }

    public void setmCVVCard(String mCVVCard) {
        this.mCVVCard = mCVVCard;
    }

    public String getmPostalCode() {
        return mPostalCode;
    }

    public void setmPostalCode(String mPostalCode) {
        this.mPostalCode = mPostalCode;
    }

    public String getmContryCode() {
        return mContryCode;
    }

    public void setmContryCode(String mContryCode) {
        this.mContryCode = mContryCode;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
