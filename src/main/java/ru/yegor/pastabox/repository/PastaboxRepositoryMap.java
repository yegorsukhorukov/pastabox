package ru.yegor.pastabox.repository;

import org.springframework.stereotype.Repository;
import ru.yegor.pastabox.exception.NotFoundEntityException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PastaboxRepositoryMap implements PastaboxRepository{
    private final Map<String, PastaboxEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PastaboxEntity getByHash(String hash) {
        PastaboxEntity pastaboxEntity = vault.get(hash);
        if (pastaboxEntity == null) {
            throw new NotFoundEntityException("Pastabox not found with hash = " + hash);
        }
        return pastaboxEntity;
    }

    @Override
    public List<PastaboxEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PastaboxEntity::isPublic)
                .filter(pastaboxEntity -> pastaboxEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(PastaboxEntity::getId))
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PastaboxEntity pastaboxEntity) {
        vault.put(pastaboxEntity.getHash(), pastaboxEntity);
    }
}
