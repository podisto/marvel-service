package com.simba.marvelservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-06-16
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RepoResponse implements Serializable {
    private Long id;
    private String name;
    @JsonProperty("html_url")
    private String url;
    private Boolean fork;
}
