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

import com.google.api.control.ServiceManagementConfigFilter;
import com.google.api.control.extensions.appengine.GoogleAppEngineControlFilter;
import com.google.api.server.spi.guice.EndpointsModule;
import com.google.common.collect.ImmutableList;
import com.googlecode.objectify.ObjectifyFilter;
import com.super8bit.bolt.endpoints.Movements;

import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

// [START endpoints_module]
public class EchoEndpointModule extends EndpointsModule {
	@Override
	public void configureServlets() {
		super.configureServlets();

		bind(ServiceManagementConfigFilter.class).in(Singleton.class);
		filter("/_ah/api/*").through(ServiceManagementConfigFilter.class);

		Map<String, String> apiController = new HashMap<>();
		apiController.put("endpoints.projectId", "nordwand-555");
		apiController.put("endpoints.serviceName", "nordwand-555.appspot.com");

		bind(GoogleAppEngineControlFilter.class).in(Singleton.class);
		filter("/_ah/api/*").through(GoogleAppEngineControlFilter.class, apiController);

		bind(Movements.class).toInstance(new Movements());
		configureEndpoints("/_ah/api/*", ImmutableList.of(Movements.class));
	}
}
// [END endpoints_module]