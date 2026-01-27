package com.nlitvins.social_media.domain.usecase.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.outbound.repository.fake.UserRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserCreateUseCaseTest {

    private UserCreateUseCase sut;

    private UserRepositoryFake userRepository;

    @BeforeAll
    void setup() {
        userRepository = new UserRepositoryFake();
        sut = new UserCreateUseCase(userRepository);
    }

    @BeforeEach
    void clear() {
        userRepository.clear();
    }

    @Test
    void createUser() {
        User before = userRepository.findById(1);
        assertNull(before);

        User User = givenUserFirst();
        User result = sut.addUser(User);
        assertNotNull(result);

        User savedUser = userRepository.findById(1);
        assertNotNull(savedUser);
    }

    private User givenUserFirst() {
        return userRepository.save(
                User.builder()
                        .id(1)
                        .userName("testUser1")
                        .build()
        );
    }
}
