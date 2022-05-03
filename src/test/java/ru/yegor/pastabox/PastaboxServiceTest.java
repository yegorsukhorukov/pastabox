package ru.yegor.pastabox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.yegor.pastabox.api.response.PastaboxResponse;
import ru.yegor.pastabox.exception.NotFoundEntityException;
import ru.yegor.pastabox.repository.PastaboxEntity;
import ru.yegor.pastabox.repository.PastaboxRepository;
import ru.yegor.pastabox.service.PastaboxService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PastaboxServiceTest {
    @Autowired
    private PastaboxService pastaboxService;

    @MockBean
    private PastaboxRepository pastaboxRepository;

    @Test
    public void notExistHash() {
        when(pastaboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pastaboxService.getByHash("awdhaowgfdoawfgoa"));
    }

    @Test
    public void getExistHash() {
        PastaboxEntity entity = new PastaboxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pastaboxRepository.getByHash("1")).thenReturn(entity);

        PastaboxResponse expected = new PastaboxResponse("11", true);
        PastaboxResponse actual = pastaboxService.getByHash("1");

        assertEquals(expected, actual);
    }
}
