package ru.app.rz0.vyngtestapp.data.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import ru.app.rz0.vyngtestapp.models.search.GiphySearchResponse;
import ru.app.rz0.vyngtestapp.models.search.VideoItem;

public class GiphySearchDeserializer implements JsonDeserializer<GiphySearchResponse> {

  @Override
  public GiphySearchResponse deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    GiphySearchResponse response = new GiphySearchResponse();
    JsonObject jsonObject = json.getAsJsonObject();
    JsonArray data = jsonObject.get("data").getAsJsonArray();

    for (int i = 0; i < data.size(); i++) {
      JsonElement element = data.get(i);
      String key = element.getAsJsonObject().get("id").getAsString();

      String imageUrl = getImageUrl(element);
      String videoUrl = getVideoUrl(element);
      response.add(new VideoItem(key, imageUrl, videoUrl));
    }

    return response;
  }

  private String getImageUrl(JsonElement element) {
    return element.getAsJsonObject().get("images").getAsJsonObject()
        .get("fixed_height_small_still").getAsJsonObject().get("url").getAsString();
  }

  private String getVideoUrl(JsonElement element) {
    return element.getAsJsonObject().get("images").getAsJsonObject().get("original_mp4")
        .getAsJsonObject().get("mp4").getAsString();
  }
}