package com.mini4.aiLibrary.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String title;
    private String author;
    private String contents;
    private String cover;
    private List<String> hashTags;
}
