package com.example.lecturecore;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.lecturecore.ticket.domain.Ticket;
import com.example.lecturecore.ticket.repository.TicketRepository;
import com.example.lecturecore.ticket.repository.UserTicketRepository;
import com.example.lecturecore.ticket.service.TicketService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class ConcurrentTest {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserTicketRepository userTicketRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @AfterEach
    void afterEach() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
        userTicketRepository.deleteAll();
        ticketRepository.deleteAll();
    }

    @Test
    void 여러명_응모() throws InterruptedException {

        Ticket savedTicket = ticketRepository.save(new Ticket(1000, 1L));

        int threadCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            String userId = String.valueOf(i);
            executorService.submit(() -> {
                try {
                    ticketService.issueWithJPA(savedTicket.getId(), userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime + "ms");

        long count = userTicketRepository.count();
        assertThat((count)).isEqualTo(1000L);
    }

    @Test
    void 여러명_응모2() throws InterruptedException {

        Ticket savedTicket = ticketRepository.save(new Ticket(1000, 1L));

        int threadCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            String userId = String.valueOf(i);
            executorService.submit(() -> {
                try {
                    ticketService.issueWithRedisTransaction(savedTicket.getId(), userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime + "ms");

        long count = userTicketRepository.count();
        assertThat((count)).isEqualTo(1000L);
    }

    @Test
    void 여러명_응모3() throws InterruptedException {

        Ticket savedTicket = ticketRepository.save(new Ticket(1000, 1L));

        int threadCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            String userId = String.valueOf(i);
            executorService.submit(() -> {
                try {
                    ticketService.issueWithKafka(savedTicket.getId(), userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime + "ms");

        long count = userTicketRepository.count();
        assertThat((count)).isEqualTo(1000L);
    }

}
