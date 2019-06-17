package com.simba.marvelservice.service;

import com.simba.marvelservice.client.GithubClient;
import com.simba.marvelservice.domain.RepoResponse;
import com.simba.marvelservice.domain.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-06-16
 */
@Slf4j
@Service
public class GithubServiceImpl implements GithubService {
    private final GithubClient githubClient;

    public GithubServiceImpl(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public Flux<UserResponse> findByUsername(String username) {
        log.info("--- get all users in {} --- ", this.getClass().getName());
        Flux<UserResponse> response = githubClient.getUsers().filter(userResponse -> username.equals(userResponse.getLogin()));
        log.info("--- get all users in {} response: {} --- ", this.getClass().getName(), response);
        return response;
    }

    @Override
    public Flux<RepoResponse> findRepoByUsername(String username) {
        log.info("--- get user's repos in {} for {} --- ", this.getClass().getName(), username);
        Flux<RepoResponse> response = githubClient.getRepoByUsername(username);
        log.info("--- get all users in {} response: {} --- ", this.getClass().getName(), response);
        return response;
    }
}
