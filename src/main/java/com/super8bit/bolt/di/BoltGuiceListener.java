/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.super8bit.bolt.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.googlecode.objectify.ObjectifyService;
import com.super8bit.bolt.entities.Movements.Movement;

import javax.servlet.ServletContextEvent;
import java.util.logging.Logger;

// [START injector]
public class BoltGuiceListener extends GuiceServletContextListener {

	private static final Logger log = Logger.getLogger(BoltGuiceListener.class.getName());

	@Override public void contextDestroyed(ServletContextEvent event) { super.contextDestroyed(event); }
	@Override protected Injector getInjector() {
		return Guice.createInjector(new EchoEndpointModule(), new BusinessLogicModule(), new AppServletModule());
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		// Register objectify entities here
		ObjectifyService.register(Movement.class);

		// log success in creating the app service
		log.info("Context created");
	}
}
// [END injector]