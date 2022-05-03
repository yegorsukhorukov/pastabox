package ru.yegor.pastabox.api.request;

import lombok.Data;

@Data
public class PastaboxRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
