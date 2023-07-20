package com.neeraj.reactiveapi.Controller;


import com.neeraj.reactiveapi.Dto.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.logging.Logger;

@RestController
public class FooController {

    private static final Logger logger = Logger.getLogger(FooController.class.getName());


    @GetMapping(value = "/foos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Foo> getResourcesByInterval() {

        logger.info("api calling - foos");

        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> new Foo(sequence.intValue(),"Foo #" + sequence));
    }

    @GetMapping("/helloMono")
    public Mono<String> helloMono() {
        logger.info("calling simple api for saying hello using mono");
        return Mono.just("Hello, reactive!");
    }

    @GetMapping(value = "/helloFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> helloFlux() {

        logger.info("calling simple api for just flex");
        Flux<String> flux = Flux.just("A", "B", "C");

        return flux;



    }
}
