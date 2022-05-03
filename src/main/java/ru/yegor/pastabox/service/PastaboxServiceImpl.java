package ru.yegor.pastabox.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.yegor.pastabox.api.request.PastaboxRequest;
import ru.yegor.pastabox.api.request.PublicStatus;
import ru.yegor.pastabox.api.response.PastaboxResponse;
import ru.yegor.pastabox.api.response.PastaboxUrlResponse;
import ru.yegor.pastabox.repository.PastaboxEntity;
import ru.yegor.pastabox.repository.PastaboxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class PastaboxServiceImpl implements PastaboxService {
    private String host;
    private int publicListSize;

    private final PastaboxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PastaboxResponse getByHash(String hash) {
        PastaboxEntity pastaboxEntity = repository.getByHash(hash);
        return new PastaboxResponse(pastaboxEntity.getData(), pastaboxEntity.isPublic());
    }

    @Override
    public List<PastaboxResponse> getFirstPublicPastaboxes() {
        List<PastaboxEntity> list = repository.getListOfPublicAndAlive(publicListSize);
        return list.stream().map(pastaboxEntity ->
            new PastaboxResponse(pastaboxEntity.getData(), pastaboxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PastaboxUrlResponse create(PastaboxRequest request) {
        int hash = generateId();
        PastaboxEntity pastaboxEntity = new PastaboxEntity();
        pastaboxEntity.setData(request.getData());
        pastaboxEntity.setId(hash);
        pastaboxEntity.setHash(Integer.toHexString(hash));
        pastaboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pastaboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pastaboxEntity);
        return new PastaboxUrlResponse(host + "/" + pastaboxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
