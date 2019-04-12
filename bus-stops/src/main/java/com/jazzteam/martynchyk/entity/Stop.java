package com.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Stop {
    private int capacity;
    private boolean[] places;

    public Stop(int capacity) {
        places = new boolean[capacity];
        Arrays.fill(places, false);
        this.capacity = capacity;
    }

    public boolean isFree() {
        int numberOfOccupied = 0;
        for (int i = 0; i < capacity; i++) {
            if (places[i] == true) {
                numberOfOccupied++;
            }
        }
        if (numberOfOccupied == capacity) {
            return false;
        } else {
            return true;
        }
    }

    public int takeFreePlace() {
        int outIndex=-1;
        for (int i = 0; i < capacity; i++) {
            if (places[i] == false) {
                places[i] = true;
                outIndex=i;
                break;
            }
        }
        return outIndex;
    }

    public void freePlace(int index){
        places[index]=false;
    }

    @Override
    public String toString() {
        return Arrays.toString(places)+"  ";
    }
}
