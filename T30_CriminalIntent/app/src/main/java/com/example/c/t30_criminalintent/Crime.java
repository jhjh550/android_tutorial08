package com.example.c.t30_criminalintent;

import java.util.UUID;

/**
 * Created by c on 2015-08-22.
 */
public class Crime {
    private String mTitle;
    private UUID mId;

    public Crime(){
        mId = UUID.randomUUID();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }
}
