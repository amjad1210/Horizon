package com.horizon.controller;

import com.horizon.config.WebSecurityConfig;
import com.horizon.model.Port;
import com.horizon.service.NetworkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Amjad on 17/11/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NetworkController.class)
@Import(WebSecurityConfig.class)
public class NetworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NetworkService networkService;

    @Test
    public void getPorts_ShouldReturnPortEntries() throws Exception {
        Port first = new Port();
        first.setActive(true);
        first.setPort(1);
        first.setDescription("description");

        Port second = new Port(2);
        second.setActive(false);
        second.setDescription("description");

        when(networkService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/port/all"))
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].port", is(1)))
                .andExpect(jsonPath("$[0].description", is("description")))

                .andExpect(jsonPath("$[1].active", is(false)))
                .andExpect(jsonPath("$[1].port", is(2)))
                .andExpect(jsonPath("$[1].description", is("description")));

        verify(networkService, times(1)).findAll();
        verifyNoMoreInteractions(networkService);
    }

    @Test
    public void bind_ShouldReturnCompletedFuture() throws Exception {
        //PortService.PortRequest bindPortRequest = new PortService.PortRequest();
        //bindPortRequest.setPort(0);

        //when(portService.bindPort(bindPortRequest)).thenReturn(null);

       // mockMvc.perform(post("/port/bind"))

    }

}