package com.ll.steamAPI_test_2025_04_02.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Steam API 기본 URL 설정
@FeignClient(name = "steamClient", url = "https://api.steampowered.com")
public interface SteamFeignClient {

    // GET 요청을 해당 엔드포인트로 보냄
    @GetMapping("/ISteamUser/GetPlayerSummaries/v2/")
    SteamResponse getPlayerSummaries(
            // API 키와 steamId 를 쿼리 파라미터로 전달
            @RequestParam("key") String apiKey,
            @RequestParam("steamids") String steamIds
    );

    @GetMapping("IPlayerService/GetOwnedGames/v1/")
    SteamGameResponse getPlayerOwnedGames(
            @RequestParam("key") String apiKey,
            @RequestParam("steamid") String steamId
    );
}
