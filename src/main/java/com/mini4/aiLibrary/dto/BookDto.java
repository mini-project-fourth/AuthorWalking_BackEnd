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
        private String contents;

        @Builder
        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .contents(contents)
                    .build();
        }
    }

}
