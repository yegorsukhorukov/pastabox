package ru.yegor.pastabox.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PastaboxEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
