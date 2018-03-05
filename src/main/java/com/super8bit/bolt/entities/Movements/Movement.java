package com.super8bit.bolt.entities.Movements;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Data
@Entity
@Cache
public class Movement {

	@Id public long Id;

	public static Movement findByKey(String key) {
		return findByKey(KeyFactory.stringToKey(key));
	}

	public static Movement findByKey(com.google.appengine.api.datastore.Key key) {
		return (Movement) ofy().load().value(key).now();
	}

	public static Movement findById(Long id) {
		return ofy().load().type(Movement.class).id(id).now();
	}

	public Key<Movement> save()	{
		return ofy().save().entity(this).now();
	}

	public static Map<Key<Movement>, Movement> saveAll(List<Movement> movements)	{
		return ofy().save().entities(movements).now();
	}

	public void remove()	{
		ofy().delete().entity(this).now();
	}

	public static void removeAll(List<Movement> movements) {
		ofy().delete().entities(movements).now();
	}
}
