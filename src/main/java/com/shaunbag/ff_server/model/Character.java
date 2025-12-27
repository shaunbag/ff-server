package com.shaunbag.ff_server.model;

import jakarta.persistence.Id;

public class Character {

	@Id
	private Long id;
	private String name;
	private Integer skill;
	private Integer luck;
	private Integer stamina;
	private Integer gold;

	public Character(Long id, String name, Integer skill, Integer luck, Integer stamina, Integer gold) {
		this.id  = id;
		this.name = name;
		this.skill = skill;
		this.luck = luck;
		this.stamina = stamina;
		this.setGold(gold);
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

	public Integer getSkill() {
		return skill;
	}

	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	public Integer getLuck() {
		return luck;
	}

	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	public Integer getStamina() {
		return stamina;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}
	
}
