package com.simba.marvelservice.client;

import com.simba.marvelservice.domain.RepoResponse;
import com.simba.marvelservice.domain.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-06-16
 */
@Component
@Slf4j
public class GithubClient {
    private final WebClient webClient;

    public GithubClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<UserResponse> getUsers() {
        log.info("--- getting all github users ---");
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserResponse.class);
    }

    public Flux<RepoResponse> getRepoByUsername(String username) {
        log.info("--- getting all repositories for {} ---", username);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .bodyToFlux(RepoResponse.class);
    }
}
