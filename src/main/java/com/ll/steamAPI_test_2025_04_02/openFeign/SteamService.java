package com.ll.steamAPI_test_2025_04_02.openFeign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SteamService {

    private final SteamFeignClient steamFeignClient;
    private final SteamStoreFeignClient steamStoreFeignClient;

    public Map<String, String> getPlayerInfo(String apiKey, String steamId) {
        SteamResponse steamResponse = steamFeignClient.getPlayerSummaries(apiKey, steamId);
        Player player = steamResponse.response().players().getFirst();
        Map<String, String> response = new HashMap<>();

        response.put("steamId", player.steamId());
        response.put("nickname", player.nickname());
        response.put("avatar", player.avatar());

        String url = player.profileUrl();
        int lastSlashIndex = url.lastIndexOf("/");
        String username = url.substring(url.indexOf("id/") + 3, lastSlashIndex);
        response.put("username", username);

        return response;
    }

    public Map<String, Integer> getPlayerGenres(String apikey, String steamId) {
        SteamGameResponse steamGameResponse = steamFeignClient.getPlayerOwnedGames(apikey, steamId);

        Map<String, Integer> genreCountMap = new HashMap<>();
        List<Game> gameList = steamGameResponse.response().games();

        int count = 0;
        for(Game game : gameList) {
            // 서비스에서는 DB 조회로
            if(count++ > 30) break;
            System.out.println(game.appId());
            System.out.println("count : " + count);

            // 해당 게임의 장르 조회
            SteamGameDetailResponse response = steamStoreFeignClient.getGameDetail(
                    Integer.valueOf(game.appId()), "KR", "genres");

            GameDetailWrapper gameDetailWrapper = response.getGames().get(game.appId());
            if (gameDetailWrapper == null || !gameDetailWrapper.isSuccess()) {
                System.out.println("Skipping game: " + game.appId());
                continue;
            }

            if(response.getGames().get(game.appId()).getGameData() == null) {
                System.out.println("Skipping game: " + game.appId());
                continue;
            }

            List<Genre> genres = response.getGames().get(game.appId()).getGameData().getGenres();
            for (Genre genre : genres) {
                genreCountMap.put(genre.getDescription(), genreCountMap.getOrDefault(genre.getDescription(), 0) + 1);
            }
        }
    return genreCountMap;
    }
}
