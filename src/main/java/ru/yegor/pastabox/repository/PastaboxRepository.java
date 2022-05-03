package ru.yegor.pastabox.repository;

import java.util.List;

public interface PastaboxRepository {
    PastaboxEntity getByHash(String hash);
    List<PastaboxEntity> getListOfPublicAndAlive(int amount);
    void add(PastaboxEntity pastaboxEntity);
}
