package com.ll.steamAPI_test_2025_04_02.openFeign;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steam")
public class SteamController {

    private final SteamService steamService;

    @Value("${custom.steam.apikey}")
    private String apikey;

    @GetMapping("/player")
    public Map<String, String> getPlayer() {
        String steamId = "76561198030440580";
        return steamService.getPlayerInfo(apikey, steamId);
    }

    @GetMapping("/genres")
    public Map<String, Integer> getGenres() {
        String steamId = "76561198030440580";
        return steamService.getPlayerGenres(apikey, steamId);
    }
}

