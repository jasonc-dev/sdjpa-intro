package jason.springframework.sdjpaintro.repositories;

import jason.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import jason.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
