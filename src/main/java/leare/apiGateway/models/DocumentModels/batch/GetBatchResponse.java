package leare.apiGateway.models.DocumentModels.batch;

import java.util.Map;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
public class GetBatchResponse {
    private boolean success;
    private Map<String, VideoInfo> value;
    // @SerializedName("value")
    // @JsonAdapter(ValueDeserializer.class)


    public GetBatchResponse(boolean success, Map<String,VideoInfo> value) {
        this.success = success;
        this.value = value;
    }


    public boolean isSuccess() {
        return this.success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String,VideoInfo> getValue() {
        return this.value;
    }

    public void setValue(Map<String,VideoInfo> value) {
        this.value = value;
    }
  

    // public static class ValueDeserializer implements JsonDeserializer<Map<String, VideoInfo>> {
    //     @Override
    //     public Map<String, VideoInfo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    //         Map<String, VideoInfo> map = new HashMap<>();
    //         JsonObject jsonObject = json.getAsJsonObject();
    //         for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
    //             String key = entry.getKey();
    //             JsonObject valueObject = entry.getValue().getAsJsonObject();
    //             VideoInfo value = context.deserialize(valueObject, VideoInfo.class);
    //             map.put(key, value);
    //         }
    //         return map;
    //     }
    // }
}