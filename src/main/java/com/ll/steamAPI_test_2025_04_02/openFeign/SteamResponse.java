package com.ll.steamAPI_test_2025_04_02.openFeign;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// 해당 구조는 스팀 응답에 따른 내용임
public record SteamResponse(
        @JsonProperty("response") PlayerResponse response
) {}

record PlayerResponse(
        @JsonProperty("players") List<Player> players
) {}

record Player(
        @JsonProperty("steamid") String steamId,
        @JsonProperty("personaname") String nickname,
        @JsonProperty("avatarfull") String avatar,
        @JsonProperty("profileurl") String profileUrl
) {}

