package com.nurayim.todoapp.models;

import javax.xml.transform.sax.SAXResult;

public class Task {

    private String title;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Task(String title)
    {
        this.title = title;
    }
}
