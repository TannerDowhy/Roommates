package com.dowhy_ehry.roommates;

/**
 * Created by tannerdowhy on 2016-11-19.
 */

public class Roommate {

    private String displayName;
    private String photoURL;
    private Room currRoom;

    public Roommate(Room currRoom, String photoURL, String displayName){
        setPhotoURL(photoURL);
        setDisplayName(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setCurrRoom (Room currRoom) {
        this.currRoom = currRoom;
    }
}
