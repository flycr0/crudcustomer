package com.testao.andikaputra.crudcustomer.repository

import org.springframework.stereotype.Repository
import com.testao.andikaputra.crudcustomer.model.User
import org.springframework.data.repository.CrudRepository
//import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface UserRepo : CrudRepository<User, String>