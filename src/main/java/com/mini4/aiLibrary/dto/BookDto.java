package com.mini4.aiLibrary.dto;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.domain.HashTag;
import lombok.*;

import java.util.List;

public class BookDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookPost {

        private String title;
        private String author;
        private String contents;
        private String cover;
        private List<String> hashTags;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookPut {

        private String title;
        private String contents;
        private String cover;
    }
}
