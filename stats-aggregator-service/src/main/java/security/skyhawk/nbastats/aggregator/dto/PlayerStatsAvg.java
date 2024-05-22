package security.skyhawk.nbastats.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PlayerStatsAvg implements Serializable {
    String playerId;
    String seasonId;
    double avgPoints;
    double avgRebounds;
    double avgAssists;
    double avgSteals;
    double avgBlocks;
    double avgFouls;
    double avgTurnovers;
    double avgMinutesPlayed;
}
