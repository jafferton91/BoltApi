package com.super8bit.bolt.services;

import com.super8bit.bolt.entities.Movements.Movement;

public interface MovementsService {

	public Movement create(Movement todo);

	public Movement update(Movement todo, Long id);

	public Movement delete(Long id);

}
