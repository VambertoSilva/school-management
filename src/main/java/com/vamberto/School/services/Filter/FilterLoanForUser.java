package com.vamberto.School.services.Filter;

import com.vamberto.School.configs.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class FilterLoanForUser {


    public static  <T> Page<T> filter(Page<T> page, Pageable pageable, Function<T, UUID> getUserIdFn){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID id = userDetails.getId();

        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));

        authentication.getAuthorities().stream().forEach( a -> System.out.println(a.getAuthority()));
        List<T> filtered = page.getContent().stream().filter(obj -> {
            if (isStudent) {
                return getUserIdFn.apply(obj).equals(id);
            }
            return true;
        }).toList();

        return new PageImpl<>(filtered, pageable, filtered.size());
    }
}
