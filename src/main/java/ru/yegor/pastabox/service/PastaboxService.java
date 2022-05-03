package ru.yegor.pastabox.service;

import ru.yegor.pastabox.api.request.PastaboxRequest;
import ru.yegor.pastabox.api.response.PastaboxResponse;
import ru.yegor.pastabox.api.response.PastaboxUrlResponse;

import java.util.List;

public interface PastaboxService {
    PastaboxResponse getByHash(String hash);
    List<PastaboxResponse> getFirstPublicPastaboxes();
    PastaboxUrlResponse create(PastaboxRequest request);
}
