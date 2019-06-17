package com.simba.marvelservice.service;

import com.simba.marvelservice.domain.RepoResponse;
import com.simba.marvelservice.domain.UserResponse;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-06-16
 */
public interface GithubService {
    Flux<UserResponse> findByUsername(String username);

    Flux<RepoResponse> findRepoByUsername(String username);
}
