package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.TestcontainersConfiguration;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    private UserRepository sut;

    @Test
    void findAll(){
        List<User> all = sut.findAll();

        assertThat(all)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void findById(){
        User user = sut.findById(1);

        assertThat(user)
                .isNotNull();
    }

    @Test
    void save(){
        User given = givenUser();
        User saved = sut.save(given);

        assertThat(saved)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(given);

        User fromDb = sut.findById(saved.getId());
        assertThat(fromDb)
                .isNotNull();
    }

    private User givenUser(){
        return User.builder()
                .userName("TestUserName")
                .build();
    }
}
