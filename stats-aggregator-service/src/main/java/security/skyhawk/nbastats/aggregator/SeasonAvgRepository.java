package security.skyhawk.nbastats.aggregator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import security.skyhawk.nbastats.aggregator.dto.PlayerStatsAvg;
import security.skyhawk.nbastats.aggregator.dto.TeamStatsAvg;
import security.skyhawk.nbastats.aggregator.model.PlayerStats;

import java.util.List;

public interface SeasonAvgRepository extends JpaRepository<PlayerStats, String> {
    @Query("""
            SELECT new security.skyhawk.nbastats.aggregator.dto.PlayerStatsAvg(
                ps.playerId,
                ps.seasonId,
                ROUND(AVG(ps.points), 2),
                ROUND(AVG(ps.rebounds), 2),
                ROUND(AVG(ps.assists), 2),
                ROUND(AVG(ps.steals), 2),
                ROUND(AVG(ps.blocks), 2),
                ROUND(AVG(ps.fouls), 2),
                ROUND(AVG(ps.turnovers), 2),
                ROUND(AVG(ps.minutesPlayed), 2)
            )
            FROM PlayerStats ps
            WHERE ps.seasonId = :seasonId
            GROUP BY ps.playerId, ps.seasonId
            """)
    List<PlayerStatsAvg> findSeasonAvgPerPlayer(@Param("seasonId") String seasonId);

    @Query("""
            SELECT new security.skyhawk.nbastats.aggregator.dto.TeamStatsAvg(
                ps.teamId,
                ps.seasonId,
                ROUND(AVG(ps.points), 2),
                ROUND(AVG(ps.rebounds), 2),
                ROUND(AVG(ps.assists), 2),
                ROUND(AVG(ps.steals), 2),
                ROUND(AVG(ps.blocks), 2),
                ROUND(AVG(ps.fouls), 2),
                ROUND(AVG(ps.turnovers), 2),
                ROUND(AVG(ps.minutesPlayed), 2)
            )
            FROM PlayerStats ps
            WHERE ps.seasonId = :seasonId
            GROUP BY ps.teamId, ps.seasonId
            """)
    List<TeamStatsAvg> findSeasonAvgPerTeam(@Param("seasonId") String seasonId);
}
