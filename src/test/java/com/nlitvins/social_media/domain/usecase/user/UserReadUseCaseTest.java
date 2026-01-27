package com.nlitvins.social_media.domain.usecase.user;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.outbound.repository.fake.UserRepositoryFake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserReadUseCaseTest {

    private UserReadUseCase sut;

    private UserRepositoryFake userRepository;

    @BeforeAll
    void setup() {
        userRepository = new UserRepositoryFake();
        sut = new UserReadUseCase(userRepository);
    }

    @BeforeEach
    void clear() {
        userRepository.clear();
    }

    @Test
    void returnEmptyListWhenGetUsers() {
        List<User> result = sut.getUsers();

        assertTrue(result.isEmpty());
    }

    @Test
    void returnListWhenGetUsersCalled() {
        User user1 = givenUserFirst();
        User user2 = givenUserSecond();

        List<User> result = sut.getUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(List.of(user1, user2).toArray()));
    }

    @Test
    void returnNullWhenUserNotFound() {
        User result = userRepository.findById(1);

        assertNull(result);
    }

    @Test
    void returnUserWhenUserFound() {
        User user = givenUserFirst();

        User result = userRepository.findById(1);

        assertNotNull(result);
        assertEquals(user, result);
    }

    private User givenUserFirst() {
        return userRepository.save(
                User.builder()
                        .id(1)
                        .userName("testUser1")
                        .build()
        );
    }

    private User givenUserSecond() {
        return userRepository.save(
                User.builder()
                        .id(2)
                        .userName("testUser2")
                        .build()
        );
    }
}
