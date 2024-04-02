package leare.apiGateway.models.DocumentModels.batch;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import leare.apiGateway.models.AuthModels.DecryptedToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CustomMapDeserializer implements JsonDeserializer<Map<String, VideoInfo>> {
    @Override
    public Map<String, VideoInfo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try{
        Gson gson = new Gson();

        Map<String, VideoInfo> map = new HashMap<>();
        JsonObject jsonObject = json.getAsJsonObject();
        System.out.println("ENTRA A CUSTOM MAP");
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            try {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                if (value.isJsonObject()) {
                    JsonObject videoInfoObject = value.getAsJsonObject();
                    VideoInfo videoInfo = gson.fromJson(videoInfoObject, VideoInfo.class);
                    map.put(key, videoInfo);
                } else {
                    map.put(key, new VideoInfo(null, null, null, null, null, 0));
                }
                // if(key.equals("13")){
                //     System.out.println(entry);
                //     System.out.println(key);
                //     System.out.println(value);
                //     VideoInfo videoInfo = gson.fromJson(value, VideoInfo.class);
                //     System.out.println(videoInfo.getFilePath());
                // }
                
                // VideoInfo videoInfo = context.deserialize(value, VideoInfo.class);
                // map.put(key, videoInfo);
            } catch (Exception e) {
                map.put(entry.getKey(), new VideoInfo(null, null, null, null, null, 0));
            }
        }
        return map;
    }
    catch(Exception e){
        System.out.println("THis is never shown");
        Map<String, VideoInfo> map = new HashMap<>();
        return map;
    }
}

}