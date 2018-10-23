package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;

import models.${NAME};

public class ${NAME}Controller extends Controller{

    @Inject
	private ObjectMapper objectMapper;

	@BodyParser.Of(BodyParser.Json.class)
	public Result create${NAME}() {
		JsonNode jsonNode = request().body().asJson();
		${NAME} i${NAME};

		try {
			i${NAME} = objectMapper.readValue(jsonNode.toString(), ${NAME}.class);
			i${NAME}.save();
			return created(Json.toJson("Created"));
		} catch(IOException e) {
			e.printStackTrace();
			return internalServerError("Error");
		}
	}

	public Result getAll${NAME}s() {
		return ok(Json.toJson(${NAME}.find.all()));
	}

	public Result get${NAME}(int id) {
		return ok(Json.toJson(${NAME}.find.byId(id)));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result update${NAME}() {
		JsonNode jsonNode = request().body().asJson();
		${NAME} i${NAME};

		try {
			i${NAME} = objectMapper.readValue(jsonNode.toString(), ${NAME}.class);
			if(${NAME}.find.byId(i${NAME}.id) != null) {
				i${NAME}.update();
				return ok(Json.toJson("Updated!"));
			}

			return notFound(Json.toJson("Not found"));
		} catch(IOException e) {
			e.printStackTrace();
			return internalServerError(Json.toJson("Error"));
		}
	}

	public Result delete${NAME}(int id) {
		if(${NAME}.find.byId(id).delete()) return ok(Json.toJson("Deleted!"));
		return notFound(Json.toJson("Not found!"));
	}
}

