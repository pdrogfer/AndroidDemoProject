package com.pgf.demoproject

class TestUtils {

    companion object {

        fun mockUserList() = List(20) { i ->
            User(
                id = i,
                firstName = "User $i first name",
                lastName = "User $i last name",
                avatarUrl = "https://picsum.photos/200"
            )
        }
    }
}