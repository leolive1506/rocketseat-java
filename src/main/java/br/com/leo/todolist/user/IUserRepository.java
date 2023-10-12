package br.com.leo.todolist.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


// <qual classe representa, tipo de id>
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByUsername(String username);
}
