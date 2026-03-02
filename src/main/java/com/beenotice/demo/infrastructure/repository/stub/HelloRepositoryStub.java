package com.beenotice.demo.infrastructure.repository.stub;

import com.beenotice.demo.domain.Choice;
import com.beenotice.demo.domain.Hello;
import com.beenotice.demo.infrastructure.repository.HelloRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class HelloRepositoryStub implements HelloRepository {

    private final AtomicInteger counter = new AtomicInteger();

    private final List<Hello> hellos;

    public HelloRepositoryStub(){
        hellos = new ArrayList<Hello>();
        hellos.add(new Hello(new Choice("Cat", "/images/cat.gif"), new Choice("Dog", "/images/dog.gif")));
        hellos.add(new Hello(new Choice("Ski", "/images/ski.gif"), new Choice("Snowboard", "/images/snowboard.gif")));
        hellos.add(new Hello(new Choice("Beach", "/images/beach.gif"), new Choice("Mountain", "/images/mountain.gif")));
    }

    public Hello getNextHello(){
        return hellos.get(counter.getAndIncrement() % hellos.size());
    }
}
