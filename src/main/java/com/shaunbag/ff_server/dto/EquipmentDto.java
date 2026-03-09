package com.shaunbag.ff_server.dto;

public record EquipmentDto(Long id, String name, String effect, Long characterId, Boolean givesBonus, Integer bonus, String skill) {
}
