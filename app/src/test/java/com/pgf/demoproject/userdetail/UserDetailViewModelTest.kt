package com.pgf.demoproject.userdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pgf.demoproject.TestUtils
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    private val userRepository: UserRepository = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: UserDetailViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        sut = UserDetailViewModel(
            userRepository = userRepository,
            userId = 0
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get user with valid id`() {

        coEvery { userRepository.getUser(any()) } returns TestUtils.mockUserList()[3]

        sut.user.observeForever {
            assert(it != null)
            assert(it!! == TestUtils.mockUserList()[3])
        }
    }
}