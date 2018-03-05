package com.super8bit.bolt.di;

import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;

/**
 * Register all the servlets & filters here so Guice can manage their dependencies
 * and parse the @Inject annotation
 * @author Omer Dawelbeit
 *
 */
public class AppServletModule extends ServletModule {

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.ServletModule#configureServlets()
	 */
	@Override
	protected void configureServlets() {

		// filters
		// Objectify filter
		filter("/*").through(ObjectifyFilter.class);
	}
}
