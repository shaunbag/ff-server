package com.shaunbag.ff_server.model;

public class Progress {

    private String book;

    private Integer section;

    public Progress(String book, Integer section) {
        this.book = book;
        this.section = section;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

}
