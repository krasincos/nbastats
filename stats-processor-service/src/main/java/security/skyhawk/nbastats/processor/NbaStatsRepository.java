package security.skyhawk.nbastats.processor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.skyhawk.nbastats.processor.model.PlayerStats;

@Repository
interface NbaStatsRepository extends JpaRepository<PlayerStats, String> {
}
