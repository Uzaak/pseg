package br.com.tiago.demo.controller

import br.com.tiago.demo.business.UserBusiness
import br.com.tiago.demo.handler.ExceptionHandler
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class UserControllerTests {

    private lateinit var userController: UserController

    private lateinit var mockMvc: MockMvc

    private var userBusiness: UserBusiness = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        userController = UserController(userBusiness)
        mockMvc = MockMvcBuilders.standaloneSetup(userBusiness)
                .setControllerAdvice(ExceptionHandler())
                .build()
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToEditUserWithNoId() {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}