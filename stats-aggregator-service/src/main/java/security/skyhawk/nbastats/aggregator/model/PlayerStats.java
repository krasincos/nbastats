package security.skyhawk.nbastats.aggregator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String playerStatsId;

    String playerId;
    String teamId;
    String seasonId;
    int points;
    int rebounds;
    int assists;
    int steals;
    int blocks;
    int fouls;
    int turnovers;
    double minutesPlayed;

}