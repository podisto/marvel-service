package com.simba.marvelservice.domain;

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
public class UserResponse implements Serializable {
    private Long id;
    private String login;
}
