package ru.yegor.pastabox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.yegor.pastabox.api.request.PublicStatus;

@Data
@RequiredArgsConstructor
public class PastaboxResponse {
    private final String data;
    private final boolean isPublic;
}
