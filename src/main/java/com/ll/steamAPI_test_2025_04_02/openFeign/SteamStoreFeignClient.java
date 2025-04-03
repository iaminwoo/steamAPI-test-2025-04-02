package com.ll.steamAPI_test_2025_04_02.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Steam Store 기본 URL 설정
@FeignClient(name = "steamStoreClient", url = "https://store.steampowered.com")
public interface SteamStoreFeignClient {

    @GetMapping("/api/appdetails")
    SteamGameDetailResponse getGameDetail(
            @RequestParam("appids") Integer appId,
            @RequestParam("cc") String countryCode,
            @RequestParam("filters") String filters
    );
}
