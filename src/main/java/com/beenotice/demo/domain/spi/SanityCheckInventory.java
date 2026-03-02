package com.beenotice.demo.domain.spi;

import com.beenotice.demo.domain.model.SanityCheck;

import java.util.List;

public interface SanityCheckInventory {
    List<SanityCheck> findAll();
}
