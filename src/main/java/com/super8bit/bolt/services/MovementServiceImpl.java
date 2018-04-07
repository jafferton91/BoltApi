package com.super8bit.bolt.services;

import com.super8bit.bolt.entities.Movements.Movement;
import com.super8bit.bolt.exceptions.ApiException;

import java.util.UUID;
import java.util.logging.Logger;

public class MovementServiceImpl implements MovementsService {

	// Java built-in logging is a lot faster on App Engine
	private static final Logger log = Logger.getLogger(MovementServiceImpl.class.getName());

	@Override
	public Movement create(Movement movement) throws ApiException {
		log.info("Creating new movement: " + movement);
		movement.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//		if(Strings.isEmptyOrWhitespace(movement.name)) { throw new ApiException(""); }
		movement.save();
		return movement;
	}

	@Override
	public Movement update(Movement todo, Long id) {
		return null;
	}

	@Override
	public Movement delete(Long id) {
		return null;
	}
}
