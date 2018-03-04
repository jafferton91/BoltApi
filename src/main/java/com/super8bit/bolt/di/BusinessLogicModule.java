package com.super8bit.bolt.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;
import com.super8bit.bolt.services.MovementServiceImpl;
import com.super8bit.bolt.services.MovementsService;

public class BusinessLogicModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {

		bind(ObjectifyFilter.class).in(Singleton.class);
		bind(MovementsService.class).to(MovementServiceImpl.class).in(Scopes.SINGLETON);
	}
}
