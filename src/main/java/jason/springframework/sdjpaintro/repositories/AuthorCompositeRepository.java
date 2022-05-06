package jason.springframework.sdjpaintro.repositories;

import jason.springframework.sdjpaintro.domain.composite.AuthorComposite;
import jason.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
