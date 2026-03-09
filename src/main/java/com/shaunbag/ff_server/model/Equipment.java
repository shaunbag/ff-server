package com.shaunbag.ff_server.model;

import jakarta.persistence.*;

import javax.annotation.Nullable;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "effect")
    private String effect;

    @Column(name = "gives_bonus")
    private Boolean givesBonus;

    @Column(name = "bonus")
    @Nullable
    private Integer bonus;

    @Column(name = "skill")
    @Nullable
    private String skill;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public Equipment() {

    }

    public Equipment(String name, String effect, Character character, Boolean givesBonus, @Nullable Integer bonus, @Nullable String skill){
        this.name = name;
        this.effect = effect;
        this.character = character;
        this.givesBonus = givesBonus;
        this.bonus = bonus;
        this.skill = skill;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Boolean getGivesBonus() {
        return givesBonus;
    }

    public void setGivesBonus(Boolean givesBonus) {
        this.givesBonus = givesBonus;
    }

    @Nullable
    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(@Nullable Integer bonus) {
        this.bonus = bonus;
    }

    @Nullable
    public String getSkill() {
        return skill;
    }

    public void setSkill(@Nullable String skill) {
        this.skill = skill;
    }
}
