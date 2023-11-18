package com.pgf.demoproject.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pgf.demoproject.TestUtils
import com.pgf.demoproject.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
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

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        coEvery { userRepository.getUsers() } returns TestUtils.mockUserList()

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