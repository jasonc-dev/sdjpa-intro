package jason.springframework.sdjpaintro.repositories;

import jason.springframework.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
