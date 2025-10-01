package com.s22009999.carrentalapp;

public class SingupClass {
    String signUsername,signEmail,signPassword,signRePassword,profile_image;
    private Object profileImageUrl;

    public SingupClass(String signUsername, String signEmail, String signPassword, String signRePassword) {
        this.signUsername = signUsername;
        this.signEmail = signEmail;
        this.signPassword = signPassword;
        this.signRePassword = signRePassword;
        this.profile_image=profile_image;
    }
    public SingupClass() {
    }

    public String getSignUsername() {
        return signUsername;
    }

    public void setSignUsername(String signUsername) {
        this.signUsername = signUsername;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getSignEmail() {
        return signEmail;
    }

    public void setSignEmail(String signEmail) {
        this.signEmail = signEmail;
    }

    public String getSignPassword() {
        return signPassword;
    }

    public void setSignPassword(String signPassword) {
        this.signPassword = signPassword;
    }

    public String getSignRePassword() {
        return signRePassword;
    }

    public void setSignRePassword(String signRePassword) {
        this.signRePassword = signRePassword;
    }


    public Object getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(Object profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}



