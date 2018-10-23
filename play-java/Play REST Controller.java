package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.UUID;

import models.${NAME};

public class ${NAME}Controller extends Controller{

	@BodyParser.Of(BodyParser.Json.class)
	public Result create${NAME}() {
		JsonNode jsonNode = request().body().asJson();
		${NAME} i${NAME};

		i${NAME} = Json.fromJson(jsonNode, ${NAME}.class);
		i${NAME}.save();

		return created(Json.toJson("Created"));
	}

	public Result getAll${NAME}s() {
		return ok(Json.toJson(${NAME}.find.all()));
	}

	public Result get${NAME}(String id) {
		return ok(Json.toJson(${NAME}.find.byId(UUID.fromString(registrationId))));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result update${NAME}() {
		JsonNode jsonNode = request().body().asJson();
		${NAME} i${NAME};

		i${NAME} = Json.fromJson(jsonNode, ${NAME}.class);

		if(${NAME}.find.byId(i${NAME}.id) != null) {
			i${NAME}.update();
			return ok(Json.toJson("Updated!"));
		}

		return notFound(Json.toJson("Not found"));
	}

	public Result delete${NAME}(String id) {
		if(${NAME}.find.byId(UUID.fromString(registrationId)).delete())
			return ok(Json.toJson("Deleted!"));

		return notFound(Json.toJson("Not found!"));
	}
}
