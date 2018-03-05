/*
 * Copyright 2016 Google Inc.
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

package com.super8bit.bolt.endpoints;

import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.inject.Inject;
import com.super8bit.bolt.entities.Email;
import com.super8bit.bolt.entities.Message;
import com.super8bit.bolt.entities.Movements.Movement;
import com.super8bit.bolt.services.MovementsService;

import java.util.logging.Logger;

/**
 * The Movements API which Endpoints will be exposing.
 */
// [START echo_api_annotation]
@Api(
	name = "bolt",
	version = "v1",
	namespace =
	@ApiNamespace(
		ownerDomain = "bolt.super8bit.com",
		ownerName = "bolt.super8bit.com",
		packagePath = ""
	),
	// [START_EXCLUDE]
	issuers = {
		@ApiIssuer(
			name = "firebase",
			issuer = "https://securetoken.google.com/YOUR-PROJECT-ID",
			jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system" + ".gserviceaccount.com"
		)
	}
// [END_EXCLUDE]
)
// [END echo_api_annotation]

public class Movements {

	private static final Logger log = Logger.getLogger(Movements.class.getName());

	@Inject
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private MovementsService movementsService;

	/**
	 * Echoes the received message back. If n is a non-negative integer, the message is copied that
	 * many times in the returned message.
	 * <p>
	 * <p>Note that name is specified and will override the default name of "{class name}.{method
	 * name}". For super8bit, the default is "bolt.bolt".
	 * <p>
	 * <p>Note that httpMethod is not specified. This will default to a reasonable HTTP method
	 * depending on the API method name. In this case, the HTTP method will default to POST.
	 */
	// [START echo_method]
	@ApiMethod(name = "echo")
	public Message echo(Message message, @Named("n") @Nullable Integer n) {
		movementsService.create(new Movement());
		return doEcho(message, n);
	}
	// [END echo_method]

	/**
	 * Echoes the received message back. If n is a non-negative integer, the message is copied that
	 * many times in the returned message.
	 * <p>
	 * <p>Note that name is specified and will override the default name of "{class name}.{method
	 * name}". For super8bit, the default is "bolt.bolt".
	 * <p>
	 * <p>Note that httpMethod is not specified. This will default to a reasonable HTTP method
	 * depending on the API method name. In this case, the HTTP method will default to POST.
	 */
	// [START echo_path]
	@ApiMethod(name = "echo_path_parameter", path = "bolt/{n}")
	public Message echoPathParameter(Message message, @Named("n") int n) {
		return doEcho(message, n);
	}
	// [END echo_path]

	/**
	 * Echoes the received message back. If n is a non-negative integer, the message is copied that
	 * many times in the returned message.
	 * <p>
	 * <p>Note that name is specified and will override the default name of "{class name}.{method
	 * name}". For super8bit, the default is "bolt.bolt".
	 * <p>
	 * <p>Note that httpMethod is not specified. This will default to a reasonable HTTP method
	 * depending on the API method name. In this case, the HTTP method will default to POST.
	 */
	// [START echo_api_key]
	@ApiMethod(name = "echo_api_key", path = "echo_api_key", apiKeyRequired = AnnotationBoolean.TRUE)
	public Message echoApiKey(Message message, @Named("n") @Nullable Integer n) {
		return doEcho(message, n);
	}
	// [END echo_api_key]

	private Message doEcho(Message message, Integer n) {
		if (n != null && n >= 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				if (i > 0) {
					sb.append(" ");
				}
				sb.append(message.getMessage());
			}
			message.setMessage(sb.toString());
		}
		return message;
	}

	/**
	 * Gets the authenticated user's email. If the user is not authenticated, this will return an HTTP
	 * 401.
	 * <p>
	 * <p>Note that name is not specified. This will default to "{class name}.{method name}". For
	 * super8bit, the default is "bolt.getUserEmail".
	 * <p>
	 * <p>Note that httpMethod is not required here. Without httpMethod, this will default to GET due
	 * to the API method name. httpMethod is added here for super8bit purposes.
	 */
	// [START google_id_token_auth]
	@ApiMethod(
		httpMethod = ApiMethod.HttpMethod.GET,
		authenticators = {EspAuthenticator.class},
		audiences = {"YOUR_OAUTH_CLIENT_ID"},
		clientIds = {"YOUR_OAUTH_CLIENT_ID"}
	)
	public Email getUserEmail(User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}

		Email response = new Email();
		response.setEmail(user.getEmail());
		return response;
	}
	// [END google_id_token_auth]

	/**
	 * Gets the authenticated user's email. If the user is not authenticated, this will return an HTTP
	 * 401.
	 * <p>
	 * <p>Note that name is not specified. This will default to "{class name}.{method name}". For
	 * super8bit, the default is "bolt.getUserEmail".
	 * <p>
	 * <p>Note that httpMethod is not required here. Without httpMethod, this will default to GET due
	 * to the API method name. httpMethod is added here for super8bit purposes.
	 */
	// [START firebase_auth]
	@ApiMethod(
		path = "firebase_user",
		httpMethod = ApiMethod.HttpMethod.GET,
		authenticators = {EspAuthenticator.class},
		issuerAudiences = {
			@ApiIssuerAudience(
				name = "firebase",
				audiences = {"YOUR-PROJECT-ID"}
			)
		}
	)
	public Email getUserEmailFirebase(User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Invalid credentials");
		}

		Email response = new Email();
		response.setEmail(user.getEmail());
		return response;
	}
	// [END firebase_auth]
}
