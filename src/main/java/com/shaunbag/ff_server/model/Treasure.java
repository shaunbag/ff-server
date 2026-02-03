package com.shaunbag.ff_server.model;

import jakarta.persistence.*;



@Entity
@Table(name = "treasure")
public class Treasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public Treasure() {
    }

    public Treasure(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
