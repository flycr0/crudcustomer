package com.testao.andikaputra.crudcustomer.controller

import com.testao.andikaputra.crudcustomer.repository.UserRepo
import com.testao.andikaputra.crudcustomer.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")


class UserController(private val userRepo: UserRepo){

  @GetMapping("/users")
  
  //fun getAllUser(): List<User> = userRepo.findAll()


    @PostMapping("/users")
    fun CreateNewUser(@RequestBody user: User): User =
    userRepo.save(user)

    @GetMapping("/users/{id}")
    fun GetUserById(@PathVariable(value = "id") id: String): ResponseEntity<User> {
        return userRepo.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/users/{id}")
    fun UpdateUserById(@PathVariable(value = "id") id: String,
                       @RequestBody NewUser: User): ResponseEntity<User> {

        return userRepo.findById(id).map { ExistingUser ->
            val UpdatedUser: User = ExistingUser.copy(NamaPertama = NewUser.NamaPertama, NamaKeluarga = NewUser.NamaKeluarga, email = NewUser.email)
            ResponseEntity.ok().body(userRepo.save(UpdatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/users/{id}")
    fun DeleteUserById(@PathVariable(value = "id") id: String): ResponseEntity<Void> {
        return userRepo.findById(id).map { user ->
            userRepo.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}