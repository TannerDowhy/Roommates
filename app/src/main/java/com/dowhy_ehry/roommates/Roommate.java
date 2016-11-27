package com.dowhy_ehry.roommates;

/**
 * Created by tannerdowhy on 2016-11-19.
 */

public class Roommate {

    private String displayName;
    private String photoURL;
    private String currRoom;
//    private Room currRoom;

    public Roommate(String currRoom, String photoURL, String displayName){
        setCurrRoom(currRoom);
        setPhotoURL(photoURL);
        setDisplayName(displayName);
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setCurrRoom (String currRoom) {
        this.currRoom = currRoom;
    }
}
