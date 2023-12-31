package com.pgf.demoproject.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pgf.demoproject.TestUtils
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepositoryImpl
import com.pgf.demoproject.ui.LoadStatus
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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

    private val userRepository: UserRepositoryImpl = mockk()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var sut : UserListViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        sut = UserListViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get users emits success state with list of users`() {

        coEvery { userRepository.getUsers() } returns TestUtils.mockUserList()

        sut.dataState.observeForever {}

        assertEquals(sut.dataState.value?.state, LoadStatus.SUCCESS)
        assert((sut.dataState.value?.data as List<User>).isNotEmpty())
    }

    @Test
    fun `get users emits error state with error message`() {

        coEvery { userRepository.getUsers() } returns null

        sut.dataState.observeForever {}

        assertEquals(sut.dataState.value?.state, LoadStatus.ERROR)
        assertEquals(sut.dataState.value?.errorMessage, "An error happened. Could not get users list. Please try again later")
        assert(sut.dataState.value?.data.isNullOrEmpty())
    }
}