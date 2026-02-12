package com.shaunbag.ff_server.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "skill")
	private Integer skill;
	@Column(name = "luck")
	private Integer luck;
	@Column(name = "stamina")
	private Integer stamina;
	@Column(name = "gold")
	private Integer gold;
	@Column
	private Integer provisions;

	@OneToMany(mappedBy = "character")
	private List<Potion> potions;

	@OneToMany(mappedBy = "character")
	private List<Equipment> equipment;

	@OneToMany(mappedBy = "character")
	private List<Progress> progressList;

	@OneToOne
	private MyUser user;


	// no args constructor required by hibernate
	protected Character() {
	}

	public Character(String name, Integer skill, Integer luck, Integer stamina, Integer gold, Integer provisions) {
		this.name = name;
		this.skill = skill;
		this.luck = luck;
		this.stamina = stamina;
		this.gold = gold;
		this.provisions = provisions;
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

	public Integer getProvisions() {
		return provisions;
	}

	public void setProvisions(Integer provisions) {
		this.provisions = provisions;
	}

	public List<Potion> getPotions() {
		return potions;
	}

	public void setPotions(List<Potion> potions) {
		this.potions = potions;
	}

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	public List<Progress> getProgressList() {
		return progressList;
	}

	public void setProgressList(List<Progress> progressList) {
		this.progressList = progressList;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}
}
