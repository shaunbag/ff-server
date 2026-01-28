package com.shaunbag.ff_server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book")
    private String book;
    @Column(name = "section")
    private Integer section;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public Progress(){

    }

    public Progress(String book, Integer section) {
        this.book = book;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
