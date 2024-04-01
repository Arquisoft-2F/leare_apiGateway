// package leare.apiGateway.models.DocumentModels.batch;
// import com.google.gson.*;
// import java.lang.reflect.Type;
// import java.util.HashMap;
// import java.util.Map;

// public class CustomMapDeserializer implements JsonDeserializer<Map<String, VideoInfo>> {

//     @Override
//     public Map<String, VideoInfo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//         Map<String, VideoInfo> map = new HashMap<>();
//         JsonObject jsonObject = json.getAsJsonObject();

//         for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
//             String key = entry.getKey();
//             VideoInfo value = context.deserialize(entry.getValue(), VideoInfo.class); // Assuming VideoInfo is your class representing the value
//             map.put(key, value);
//         }

//         return map;
//     }
// }