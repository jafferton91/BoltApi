package com.super8bit.bolt.services;

import com.super8bit.bolt.entities.Movements.Movement;
import com.super8bit.bolt.exceptions.ApiException;

public interface MovementsService {

	Movement create(Movement todo) throws ApiException;

	Movement update(Movement todo, Long id);

	Movement delete(Long id);

}
