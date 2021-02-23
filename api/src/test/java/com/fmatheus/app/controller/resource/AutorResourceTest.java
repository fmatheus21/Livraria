package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.rule.AutorRule;
import com.fmatheus.app.model.entity.AutorEntity;
import com.fmatheus.app.model.persistence.service.impl.AutorServiceImpl;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author fmatheus
 */
@WebMvcTest(AutorResource.class)
public class AutorResourceTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AutorServiceImpl service;

    @MockBean
    private AutorRule rule;

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void findAllPaginator() throws Exception {
        Mockito.when(this.service.findAll()).thenReturn(this.returnList());
        this.mockMvc.perform(get("/autores"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findById() throws Exception {
        Mockito.when(this.service.findById(5)).thenReturn(this.returnObject(5));
        this.mockMvc.perform(get("/autores/{id}", 5))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteEndpoint() throws Exception {

        Mockito.when(this.service.findById(5)).thenReturn(this.returnObject(5));
        this.mockMvc.perform(
                delete("/autores/{id}", 5))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {

        Mockito.when(this.service.save(this.createObject())).thenReturn(this.createObject());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/autores")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.converterJson())
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private String converterJson() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("id", 5);
        obj.put("data_cadastro", LocalDateTime.now());
        obj.put("data_alteracao", LocalDateTime.now());
        return obj.toString();
    }

    private AutorEntity createObject() {
        var autor = new AutorEntity();
        autor.setId(5);
        autor.setDataCadastro(LocalDateTime.now());
        autor.setDataAlteracao(LocalDateTime.now());
        return autor;
    }

    private List<AutorEntity> returnList() {
        List<AutorEntity> list = new ArrayList<AutorEntity>();
        var autor = new AutorEntity();
        autor.setId(5);
        autor.setDataCadastro(LocalDateTime.now());
        autor.setDataAlteracao(LocalDateTime.now());
        list.add(autor);
        return list;
    }

    private Optional<AutorEntity> returnObject(int id) {
        var autor = new AutorEntity();
        autor.setId(id);
        autor.setDataCadastro(LocalDateTime.now());
        autor.setDataAlteracao(LocalDateTime.now());
        return Optional.ofNullable(autor);
    }

}
