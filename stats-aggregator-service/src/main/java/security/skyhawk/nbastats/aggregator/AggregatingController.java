package security.skyhawk.nbastats.aggregator;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import security.skyhawk.nbastats.aggregator.dto.PlayerStatsAvg;
import security.skyhawk.nbastats.aggregator.dto.TeamStatsAvg;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stats/avg")
@RequiredArgsConstructor
public class AggregatingController {

    private final SeasonAvgService service;

    @Cacheable("players-avg")
    @GetMapping("/players")
    public List<PlayerStatsAvg> getSeasonAvgByPlayer(@RequestParam String seasonId) {
        return service.getSeasonAvgByPlayer(seasonId);
    }

    @Cacheable("teams-avg")
    @GetMapping("/teams")
    public List<TeamStatsAvg> geSeasonAvgByTeam(@RequestParam String seasonId) {
        return service.geSeasonAvgByTeam(seasonId);
    }
}
