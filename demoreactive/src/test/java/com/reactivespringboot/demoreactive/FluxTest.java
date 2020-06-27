package com.reactivespringboot.demoreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void fluxTest() {

        Flux<String> flux = Flux.just("kaka", "Spring", "Spring Boot");
        Flux<String> stringFlux = flux.map(s -> s.concat(" Java"));

        stringFlux.subscribe(System.out::println);

    }

    @Test
    public void fluxTestElements_WithoutError() {
        Flux<String> flux = Flux.just("kaka", "Spring", "Spring Boot").log();
        StepVerifier.create(flux)
                .expectNext("kaka")
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .verifyComplete();

    }

    @Test
    public void monoTest() {

        Mono<String> mono = Mono.just("Spring");
        StepVerifier.create(mono.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTest_Error() {
        StepVerifier.create(Mono.error(new RuntimeException("Exception Occured")).log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
