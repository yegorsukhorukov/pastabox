package ru.yegor.pastabox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PastaboxUrlResponse {
    private final String url;
}
