package jason.springframework.sdjpaintro;

import jason.springframework.sdjpaintro.domain.AuthorUuid;
import jason.springframework.sdjpaintro.domain.BookNatural;
import jason.springframework.sdjpaintro.domain.BookUuid;
import jason.springframework.sdjpaintro.domain.composite.AuthorComposite;
import jason.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import jason.springframework.sdjpaintro.domain.composite.NameId;
import jason.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"jason.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // don't auto config datasource
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testAuthorEmbedded() {
        NameId nameId = new NameId("Jason", "C");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorComposite() {
        NameId nameId = new NameId("Jason", "C");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(authorComposite.getLastName());
        authorComposite.setCountry("UK");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void testBookNatural() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testSavedBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId()).isNotNull();

        BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testSaveAuthorUUID() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }
}
