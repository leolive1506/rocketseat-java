package br.com.leo.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  /**
   * Alguns tipos de retorno:
   * - string
   * - integer
   * - double
   * - float
   * - char
   * - date
   * - void
   */
  // user model vem do body da req
  @PostMapping("/")
  public void create(@RequestBody UserModel userModel) {
    // print
    System.out.println(userModel.getUsername());
  }
}
