package com.example.testtask;

import com.example.testtask.controllers.ApiController;
import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    private static final String UUID_STR  = "bb67ba48-c764-4886-8503-fe874b71c4a6";
    @Mock
    private WalletService walletService;
    @InjectMocks
    private ApiController apiController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getBalance() throws Exception{
        mockMvc.perform(get("/api/v1/wallets/{id}", UUID_STR))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(0));
        verify(walletService, times(1)).getBalance(UUID.fromString(UUID_STR));
    }

    @Test
    void deposit() throws Exception {
        UUID uuid = UUID.fromString(UUID_STR);
        WalletOperationRequest depositOperation = new WalletOperationRequest(uuid, "DEPOSIT", 1000);
        String operationRequestJson = objectMapper.writeValueAsString(depositOperation);
        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(operationRequestJson))
                .andExpect(status().isOk());
        verify(walletService, times(1)).deposit(depositOperation);
    }

    @Test
    void withdraw() throws Exception {
        UUID uuid = UUID.fromString(UUID_STR);
        WalletOperationRequest withdrawOperation = new WalletOperationRequest(uuid, "WITHDRAW", 1000);
        String operationRequestJson = objectMapper.writeValueAsString(withdrawOperation);
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(operationRequestJson))
                .andExpect(status().isOk());
        verify(walletService, times(1)).withdraw(withdrawOperation);
    }
}
