package com.example.development.sakaiclient20.models.sakai.assignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class DueTime implements Serializable
{

    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("time")
    @Expose
    private long time;
    private final static long serialVersionUID = -4887056960656050957L;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("display", display).append("time", time).toString();
    }

}
