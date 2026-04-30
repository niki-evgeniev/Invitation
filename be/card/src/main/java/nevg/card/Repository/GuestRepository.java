package nevg.card.Repository;

import nevg.card.Models.Entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByEmail(String email);

    @Query("""
    SELECT DISTINCT g
    FROM Guest g
    LEFT JOIN FETCH g.followers
""")
    List<Guest> findAllWithFollowers();
}
