package ru.otus.elena363404.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.elena363404.rest.PagesController;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagesController.class)
public class SecuredControllerWebMvcIntegrationTest  {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DataSource dataSource;


  @WithMockUser(value = "admin",
                authorities = {"ADMIN"})
  @Test
  public void testAuthenticatedOnAdmin() throws Exception {
    mockMvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

  }
}
