package com.pgf.demoproject

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pgf.demoproject.userlist.UserListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    private val userRepository: UserRepository = mockk()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var sut : UserListViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockUsers = List(20) { i ->
        User(
            id = i,
            firstName = "User $i first name",
            lastName = "User $i last name",
            avatarUrl = "https://picsum.photos/200"
        )
    }

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.i(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0

        Dispatchers.setMain(testDispatcher)

        coEvery { userRepository.getUsers() } returns mockUsers

        sut = UserListViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getUsers is called then return a list of users`() {

        sut.userList.observeForever {
            assert(it != null)
            assert(it!!.isNotEmpty())

        }
    }
}