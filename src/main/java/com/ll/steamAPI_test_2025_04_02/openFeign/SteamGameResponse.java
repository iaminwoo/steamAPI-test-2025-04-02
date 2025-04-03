package com.ll.steamAPI_test_2025_04_02.openFeign;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SteamGameResponse(
        @JsonProperty("response") GameResponse response
) {}

record GameResponse(
        @JsonProperty("games") List<Game> games,
        @JsonProperty("game_count") int gameCount
) {}

record Game(
        @JsonProperty("appid") String appId,
        @JsonProperty("playtime_forever") int playtime
) {}
