package com.vamberto.School.services.Filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterPage {
    public static  <T> Page<T> filter(Page<T> page, Predicate<T> filter, Pageable pageable){
        List<T> filtered = page.getContent().stream().filter(filter).toList();

        return new PageImpl<>(filtered, pageable, filtered.size());
    }
}
