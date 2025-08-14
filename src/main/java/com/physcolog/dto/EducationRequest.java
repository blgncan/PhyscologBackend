package com.physcolog.dto;

import lombok.Data;

@Data
public class EducationRequest {
    private String title;
    private String date;
    private Long about_id; // Gelen JSON verisini doğrudan almak için
}
