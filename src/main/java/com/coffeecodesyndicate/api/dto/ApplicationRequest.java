package com.coffeecodesyndicate.api.dto;

public class ApplicationRequest {

    private String formTitle;
    private String formBody;
    private Integer userId;
    private Integer petId;

    public String getFormTitle() {
        return formTitle;
    }

    public String getFormBody() {
        return formBody;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public void setFormBody(String formBody) {
        this.formBody = formBody;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }
}
