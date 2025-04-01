package com.vamberto.School.services;

import com.vamberto.School.models.Book;
import com.vamberto.School.repositories.BookRepository;
import com.vamberto.School.repositories.ClassificationRepository;
import lombok.RequiredArgsConstructor;
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


    public List<Book> listBook(){
        return bookRepository.findAll();
    }

    public void delete(String id){
        bookRepository.deleteById(UUID.fromString(id));
    }




}
