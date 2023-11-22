package com.pgf.demoproject.userdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pgf.demoproject.TestUtils
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.ui.LoadStatus
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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
    fun `get user with valid id emits success state`() {

        coEvery { userRepository.getUser(any()) } returns TestUtils.mockUserList()[3]

        sut.dataState.observeForever {}

        assertEquals(sut.dataState.value?.state, LoadStatus.SUCCESS)
        assertEquals(sut.dataState.value?.data, TestUtils.mockUserList()[3])
    }

    @Test
    fun `get user with invalid id emits error state`() {

        coEvery { userRepository.getUser(any()) } returns null

        sut.dataState.observeForever {}

        assertEquals(sut.dataState.value?.state, LoadStatus.ERROR)
        assertEquals(sut.dataState.value?.errorMessage, "Could not get User with id=0")
        assert(sut.dataState.value?.data == null)
    }
}