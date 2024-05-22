package security.skyhawk.nbastats.aggregator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import security.skyhawk.nbastats.aggregator.dto.PlayerStatsAvg;
import security.skyhawk.nbastats.aggregator.dto.TeamStatsAvg;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeasonAvgService {

    private final SeasonAvgRepository repository;

    public List<PlayerStatsAvg> getSeasonAvgByPlayer(String seasonId) {
        var seasonAvgPerPlayer = repository.findSeasonAvgPerPlayer(seasonId);
        log.info("got season avg per player: {}", seasonAvgPerPlayer);

        return seasonAvgPerPlayer;
    }

    public List<TeamStatsAvg> geSeasonAvgByTeam(String seasonId) {
        List<TeamStatsAvg> seasonAvgPerTeam = repository.findSeasonAvgPerTeam(seasonId);
        log.info("got season avg per team: {}", seasonAvgPerTeam);

        return seasonAvgPerTeam;
    }
}
