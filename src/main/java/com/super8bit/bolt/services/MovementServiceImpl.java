package com.super8bit.bolt.services;

import com.super8bit.bolt.entities.Movements.Movement;

import java.util.logging.Logger;

public class MovementServiceImpl implements MovementsService {

	// Java built-in logging is a lot faster on App Engine
	private static final Logger log = Logger.getLogger(MovementServiceImpl.class.getName());

	@Override
	public Movement create(Movement movement) {
		log.info("Creating new movement: " + movement);
		movement.save();
		return null;
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
