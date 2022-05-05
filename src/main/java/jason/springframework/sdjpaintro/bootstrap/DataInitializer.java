package jason.springframework.sdjpaintro.bootstrap;

import jason.springframework.sdjpaintro.domain.Book;
import jason.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");
        Book savedDDD = bookRepository.save(bookDDD);

        Book bookISA = new Book("Spring in Action", "234234", "Oriely");
        Book savedISA = bookRepository.save(bookISA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });
    }
}
