package com.shaunbag.ff_server.model.dto;

public record CharacterCreateDto(String name, Integer skill, Integer luck, Integer stamina, Integer gold, Integer provisions) {
}
