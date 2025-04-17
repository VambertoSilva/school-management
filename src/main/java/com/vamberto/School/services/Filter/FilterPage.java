package com.vamberto.School.services.Filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.function.Predicate;

public class FilterPage {
    public static  <T> Page<T> filter(Page<T> page, Predicate<T> filter, Pageable pageable){
        List<T> filtered = page.getContent().stream().filter(filter).toList();
        System.out.println("filtrato:" + filtered);

        return new PageImpl<>(filtered, pageable, filtered.size());
    }
}
