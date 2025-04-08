package com.vamberto.School.services;

import com.vamberto.School.models.Book;
import com.vamberto.School.models.enums.BookStatus;
import com.vamberto.School.repositories.BookRepository;
import com.vamberto.School.repositories.ClassificationRepository;
import com.vamberto.School.services.Filter.FilterPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ClassificationRepository classificationRepository;

    public Book createBook(Book book){

        book.setStatus(BookStatus.AVAILABLE);
        return bookRepository.save(book);

    }

    public Book updateBook( String id, Book updatedBook){

        UUID trueId = UUID.fromString(id);
        Optional<Book> bookOptional = bookRepository.findById(trueId);

        //nao acessivel ao aluno

        if(bookOptional.isPresent()){
            Book book = bookOptional.get();

            book.setAuthor(updatedBook.getAuthor());
            book.setTitle(updatedBook.getTitle());
            book.setStatus(updatedBook.getStatus());
            book.setPublishDate(updatedBook.getPublishDate());

            if(classificationRepository.existsById(updatedBook.getClassificationId())) {
                book.setClassificationId(updatedBook.getClassificationId());
            }else{
                throw new RuntimeException("ID invalido da classificação" + updatedBook.getClassificationId());
            }
            return bookRepository.save(book);
        }else{
            throw new RuntimeException("Aluno não encontrado" + updatedBook.getId());
        }
    }


    public Page<Book> listBook(Pageable pageable){



        return bookRepository.findAll(pageable);
    }

    public Page<Book> searchBooks(String title, int page, int size, String sortBy, String direction, BookStatus filter) {
        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            sortDirection = Sort.Direction.ASC;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        // Se eu não enviar um titulo e enviar no filtro "ALL", enviar todos os dados
        if(filter == BookStatus.ALL && title.isEmpty() ){
            return  bookRepository.findAll(pageable);

        }

        if (filter == BookStatus.ALL && (title == null || title.trim().isEmpty())) {
            return bookRepository.findAll(pageable);
        }


        Page<Book> pageBook = bookRepository.findByTitleContainingIgnoreCase(title, pageable);

        Page<Book> pageFilterBook = FilterPage.filter(pageBook, book -> book.getStatus().equals(filter), pageable);

         return pageFilterBook;

    }

    public void delete(String id){
        bookRepository.deleteById(UUID.fromString(id));
    }




}
