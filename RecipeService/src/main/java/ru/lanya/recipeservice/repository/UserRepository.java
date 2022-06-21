package ru.lanya.recipeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanya.magiccooking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

