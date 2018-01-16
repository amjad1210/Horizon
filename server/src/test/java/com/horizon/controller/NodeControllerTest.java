package com.horizon.controller;

import com.horizon.config.WebSecurityConfig;
import com.horizon.model.Node;
import com.horizon.service.NodeService;
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
@WebMvcTest(NodeController.class)
@Import(WebSecurityConfig.class)
public class NodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NodeService nodeService;

    @Test
    public void getClients_ShouldReturnClientEntries() throws Exception {
        Node first = new Node();
        first.setOnline(true);
        first.setId("id1");
        first.setLan("lan");
        //first.setPcName("pcName");
        first.setOs("os");
        first.setCpu(1);
        first.setRamUsage("ramUsage");
        first.setRamUsagePercentage(1);
        first.setUptime("uptime");
        first.setPing(1);

        Node second = new Node("id2");
        first.setOnline(false);
        second.setLan("lan");
        //second.setPcName("pcName");
        second.setOs("os");
        second.setCpu(2);
        first.setRamUsage("ramUsage");
        first.setRamUsagePercentage(2);
        second.setUptime("uptime");
        second.setPing(2);

        when(nodeService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/client/all"))
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].status", is(true)))
                .andExpect(jsonPath("$[0].id", is("id1")))
                .andExpect(jsonPath("$[0].lan", is("lan")))
                .andExpect(jsonPath("$[0].pcName", is("pcName")))
                .andExpect(jsonPath("$[0].os", is("os")))
                .andExpect(jsonPath("$[0].cpu", is(1)))
                .andExpect(jsonPath("$[0].ramUsage", is("ramUsage")))
                .andExpect(jsonPath("$[0].ramUsagePercentage", is(2)))
                .andExpect(jsonPath("$[0].uptime", is("uptime")))
                .andExpect(jsonPath("$[0].ping", is(1)))

                .andExpect(jsonPath("$[1].status", is(false)))
                .andExpect(jsonPath("$[1].id", is("id2")))
                .andExpect(jsonPath("$[1].lan", is("lan")))
                .andExpect(jsonPath("$[1].pcName", is("pcName")))
                .andExpect(jsonPath("$[1].os", is("os")))
                .andExpect(jsonPath("$[1].cpu", is(2)))
                .andExpect(jsonPath("$[1].ramUsage", is("ramUsage")))
                .andExpect(jsonPath("$[1].ramUsagePercentage", is(2)))
                .andExpect(jsonPath("$[1].uptime", is("uptime")))
                .andExpect(jsonPath("$[1].ping", is(2)));

        verify(nodeService, times(1)).findAll();
        verifyNoMoreInteractions(nodeService);
    }

}