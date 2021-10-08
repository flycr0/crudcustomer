package com.testao.andikaputra.crudcustomer.model
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long=0,
        val NamaPertama: String,
        val NamaKeluarga: String,
        val email: String
)