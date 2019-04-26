package springboot.example.com.api;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.api.BooksApi;
import io.swagger.model.Book;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BooksApiController implements BooksApi {

    public ResponseEntity<Void> createBook(
            @ApiParam(value = "Created book object", required = true) @Valid @RequestBody Book book) {
        log.info("called createdBook: " + book.toString());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookById(
            @ApiParam(value = "The id that needs to be fetched. Use 1 for testing. ", required = true) @PathVariable("id") Integer id) {
        log.info("called getBookById: " + String.valueOf(id));
        val book = new Book().id(id.longValue());
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getBooks() {
        log.info("called : getBooks");
        List<Book> books = Collections.emptyList();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

}
