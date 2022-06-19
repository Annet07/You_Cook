package ru.lanya.magiccooling.repository;

import java.util.List;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import ru.lanya.magiccooking.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Executable
  List<User> getSubscribersById(Long chefId);
}
