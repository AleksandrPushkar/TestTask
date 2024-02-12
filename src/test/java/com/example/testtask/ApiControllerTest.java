package com.example.testtask;

import com.example.testtask.controllers.ApiController;
import com.example.testtask.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ApiControllerTest {
    @Mock
    private WalletService walletService;
    @InjectMocks
    private ApiController apiController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    void getBalance() throws Exception{
        mockMvc.perform(get("/api/v1/wallets/{id}", "614126b2-a3ff-4152-96c2-19546d316671"))
                .andExpect(status().isOk())
                .andExpect(("$").value(10000));
    }
}
