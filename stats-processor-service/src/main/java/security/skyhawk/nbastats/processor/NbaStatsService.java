package security.skyhawk.nbastats.processor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import security.skyhawk.nbastats.processor.model.PlayerStats;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NbaStatsService {

    NbaStatsRepository repository;
    RedisCacheManager cacheManager;

    public void savePlayerStats(PlayerStatsDto stats) {
        cacheManager.getCache("players-avg").evict(stats.getSeasonId());
        cacheManager.getCache("teams-avg").evict(stats.getSeasonId());
        PlayerStats entity = toEntity(stats);
        repository.save(entity);
        log.info("Player stats saved to database: {}. Corresponding records evicted from cache", entity);
    }

    private static PlayerStats toEntity(PlayerStatsDto request) {
        return PlayerStats.builder()
                .playerId(request.playerId)
                .teamId(request.teamId)
                .seasonId(request.seasonId)
                .points(request.points)
                .rebounds(request.rebounds)
                .assists(request.assists)
                .steals(request.steals)
                .blocks(request.blocks)
                .fouls(request.fouls)
                .turnovers(request.turnovers)
                .minutesPlayed(request.minutesPlayed)
                .build();
    }

}