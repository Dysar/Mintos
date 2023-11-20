package com.example.mintos.repository.transaction;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.service.transaction.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
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
        Transaction transaction1 = new Transaction(1L, 100L, 200L, LocalDateTime.now(), "USD", BigDecimal.TEN);
        Transaction transaction2 = new Transaction(2L, 150L, 250L, LocalDateTime.now(), "EUR", BigDecimal.valueOf(20));

        // Mock the repository behavior
        when(transactionRepository.findBySourceAccountIDOrDestinationAccountIDEquals(100L, 200L))
                .thenReturn(Arrays.asList(transaction1, transaction2));

        // Call the repository method
        List<Transaction> result = transactionRepository.findBySourceAccountIDOrDestinationAccountIDEquals(100L, 200L);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(transaction1, result.get(0));
        assertEquals(transaction2, result.get(1));
    }
}
