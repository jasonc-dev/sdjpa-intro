package jason.springframework.sdjpaintro.repositories;

import jason.springframework.sdjpaintro.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
