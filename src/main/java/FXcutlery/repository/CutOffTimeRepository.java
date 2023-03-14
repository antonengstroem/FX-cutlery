package FXcutlery.repository;

import FXcutlery.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CutOffTimeRepository extends JpaRepository<Currency, String> {
}
