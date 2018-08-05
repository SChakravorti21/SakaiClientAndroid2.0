package com.example.development.sakaiclient20.models.sakai.assignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigInteger;

public class CloseTime implements Serializable
{

    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("time")
    @Expose
    private BigInteger time;
    private final static long serialVersionUID = 9042649548344124493L;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public BigInteger getTime() {
        return time;
    }

    public void setTime(BigInteger time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("display", display).append("time", time).toString();
    }

}
