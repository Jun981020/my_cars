package com.jproject.my_cars.domain.dealer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DealerService {
    private final DealerRepository dealerRepository;
}
