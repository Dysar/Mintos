package com.example.mintos.repository.transaction;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.service.transaction.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testFindBySourceAccountIDOrDestinationAccountIDEquals() {
        // Mock data
        Pageable pageable = Pageable.unpaged();

        Page<Transaction> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        // Mock the repository behavior
        when(transactionRepository.findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(100L, 200L, pageable))
                .thenReturn(expectedPage);

        // Call the repository method
        Page<Transaction> result = transactionRepository.findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(100L, 200L,pageable);

        // Verify the result
        assertEquals(expectedPage, result);
    }
}
