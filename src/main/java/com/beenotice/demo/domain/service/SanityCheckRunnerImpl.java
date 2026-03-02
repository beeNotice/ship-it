package com.beenotice.demo.domain.service;

import com.beenotice.demo.domain.api.SanityCheckRunner;
import com.beenotice.demo.domain.model.SanityCheck;
import com.beenotice.demo.domain.spi.SanityCheckInventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanityCheckRunnerImpl implements SanityCheckRunner {

    private final SanityCheckInventory sanityCheckInventory;

    public SanityCheckRunnerImpl(SanityCheckInventory sanityCheckInventory) {
        this.sanityCheckInventory = sanityCheckInventory;
    }

    @Override
    public SanityCheck getCheckAt(int index) {
        List<SanityCheck> sanityChecks = sanityCheckInventory.findAll();
        return sanityChecks.get(index % sanityChecks.size());
    }
}
