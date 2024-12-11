package com.example.cinevictor

fun main() {

}


class AppFoo(val repository: Repository) {
   fun foo() {
       repository.foo()
   }
}

//data
class RepositoryImpl(): Repository {
    override fun foo() {
        TODO("Not yet implemented")
    }
}

//domain
interface Repository {
    fun foo()
}




