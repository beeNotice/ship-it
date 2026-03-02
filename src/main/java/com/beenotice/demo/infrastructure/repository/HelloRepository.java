package com.beenotice.demo.infrastructure.repository;

import com.beenotice.demo.domain.Hello;

public interface HelloRepository {

    Hello getNextHello();
}
