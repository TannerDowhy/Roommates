package com.dowhy_ehry.roommates;

/**
 * Created by tannerdowhy on 2016-11-19.
 */

public class Room {
    private int occupants = 1;

    public int getOccupants() {
        return occupants;
    }

    public void incrementOccupants() {
        occupants += 1;
    }
}