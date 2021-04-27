package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.FoundException.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book(1, "Fight Club", 409, "Chuck Palahniuk", 256, 1996);
    private TShirt tShirt = new TShirt(2, "Zolla", 330, "White", "XS");

    @BeforeEach
    public void setUp() {
        repository.save(coreJava);
        repository.save(tShirt);
    }

    @Test
    public void shouldRemoveProduct() {
        repository.removeById(1);
        Product[] expected = new Product[]{tShirt};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowException() {
        int id = 3;
        String message = "Element with id: " + id + " not found";

        assertThrows(NotFoundException.class, () -> repository.removeById(id), message);
    }

}