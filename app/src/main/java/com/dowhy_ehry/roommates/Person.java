package com.dowhy_ehry.roommates;

/**
 * Created by tannerdowhy on 2016-11-27.
 */

public class Person {
    private String displayName;
    private String photoURL;
    private String currRoom;
    private String email;

    public Person(String currRoom, String photoURL, String displayName, String email){
        setCurrRoom(currRoom);
        setPhotoURL(photoURL);
        setDisplayName(displayName);
        setEmail(email);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getCurrRoom() {
        return currRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setCurrRoom (String currRoom) {
        this.currRoom = currRoom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
