package com.mini4.aiLibrary.dto;

import com.mini4.aiLibrary.domain.Book;
import lombok.*;

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

        @Builder
        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .contents(contents)
                    .cover(cover)
                    .build();
        }
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
