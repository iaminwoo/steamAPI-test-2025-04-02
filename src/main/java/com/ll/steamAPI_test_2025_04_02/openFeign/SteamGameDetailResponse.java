package com.ll.steamAPI_test_2025_04_02.openFeign;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SteamGameDetailResponse {
    private Map<String, GameDetailWrapper> games = new HashMap<>();

    @JsonAnySetter
    public void addGame(String key, GameDetailWrapper value) {
        games.put(key, value);
    }
}

@Setter
@Getter
class GameDetailWrapper {
    private boolean success;

    @JsonProperty("data")
    @JsonDeserialize(using = GameDetailDeserializer.class) // 커스텀 deserializer 적용
    private GameDetail gameData;

}

@Setter
@Getter
class GameDetail {
    @JsonProperty("genres")
    private List<Genre> genres;
}

@Setter
@Getter
class Genre {
    private String id;
    private String description;
}


