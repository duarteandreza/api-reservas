package io.github.duarteandreza.tcc.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
@FeignClient(value = "some-random-api", url = "https://some-random-api.ml")
public interface AvatarRepository {

    @GetMapping(value = "/img/dog")
    Object getAvatar();

}
