package com.example.development.sakaiclient20.models.sakai.assignments;

import com.example.development.sakaiclient20.models.interfaces.IAttachment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Attachment implements IAttachment, Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = -1319373600278001019L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("url", url).toString();
    }

}
