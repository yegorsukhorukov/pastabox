package ru.yegor.pastabox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yegor.pastabox.api.request.PastaboxRequest;
import ru.yegor.pastabox.api.response.PastaboxResponse;
import ru.yegor.pastabox.api.response.PastaboxUrlResponse;
import ru.yegor.pastabox.service.PastaboxService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PastaboxController {
    private final PastaboxService pastaboxService;

    @GetMapping("/")
    public Collection<PastaboxResponse> getPublicPastaList() {
        return pastaboxService.getFirstPublicPastaboxes();
    }

    @GetMapping("/{hash}")
    public PastaboxResponse getByHash(@PathVariable String hash) {
        return pastaboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PastaboxUrlResponse add(@RequestBody PastaboxRequest request) {
        return pastaboxService.create(request);
    }
}
