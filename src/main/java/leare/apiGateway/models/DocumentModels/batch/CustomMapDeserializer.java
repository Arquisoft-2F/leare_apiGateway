package leare.apiGateway.models.DocumentModels.batch;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CustomMapDeserializer implements JsonDeserializer<Map<String, VideoInfo>> {
    @Override
    public Map<String, VideoInfo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, VideoInfo> map = new HashMap<>();
        JsonObject jsonObject = json.getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            VideoInfo videoInfo = context.deserialize(value, VideoInfo.class);
            map.put(key, videoInfo);
        }

        return map;
    }
}