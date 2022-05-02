package ru.yegor.pastabox.controller;

import org.springframework.web.bind.annotation.*;
import ru.yegor.pastabox.api.request.PastaBoxRequest;

import java.util.Collection;
import java.util.Collections;

@RestController
public class PastaBoxController {

    @GetMapping("/")
    public Collection<String> getPublicPastaList() {
        return Collections.emptyList();
    }

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash) {
        return hash;
    }

    @PostMapping("/")
    public String add(@RequestBody PastaBoxRequest request) {
        return request.getData();
    }
}
