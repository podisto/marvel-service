package com.simba.marvelservice.controller;

import com.simba.marvelservice.domain.RepoResponse;
import com.simba.marvelservice.domain.UserResponse;
import com.simba.marvelservice.service.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.retry.Retry;

import java.time.Duration;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-06-16
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public Flux<RepoResponse> getUsers(@PathVariable("username") String username) {
        log.info("--- get all repositories for {} in {} --- ", username, this.getClass().getName());
        Flux<RepoResponse> response = githubService.findByUsername(username)
                .retryWhen(Retry.any().fixedBackoff(Duration.ofSeconds(2)).retryMax(5))
                .onErrorReturn(new UserResponse(1L, "podisto"))
                .flatMap(userResponse -> githubService.findRepoByUsername(userResponse.getLogin()))
                .retryWhen(Retry.any().fixedBackoff(Duration.ofMillis(1000)).retryMax(1))
                .onErrorReturn(new RepoResponse(1L, "podisto", null, false));
        log.info("--- get all users in {} response: {} --- ", this.getClass().getName(), response);
        return response;
    }
}
