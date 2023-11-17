package com.pgf.demoproject

class UserRepository {

    private val mockUsers = List(20) { i ->
        User(
            id = i,
            firstName = "User $i first name",
            lastName = "User $i last name",
            avatarUrl = "https://picsum.photos/200"
        )
    }

    fun getUsers(): List<User> {
        return mockUsers
    }
}