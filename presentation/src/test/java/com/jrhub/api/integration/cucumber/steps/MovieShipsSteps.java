package com.jrhub.api.integration.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.jrhub.api.dto.MovieSpaceShipsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ActiveProfiles("test")
@Slf4j
public class MovieShipsSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<MovieSpaceShipsDto> response;
    private MovieSpaceShipsDto resultMovieSpaceShipsDto;

    @Given("existe una nave espacial con el ID {long}")
    public void existSpaceShipById(long shipId) throws Exception {
        log.info("Ejecutado el GIVEN - Testing ship with ID: " + shipId);

        // Realizar petición HTTP GET real
        String url = "http://localhost:" + port + "/api/v1/space-ships/" + shipId;
        response = restTemplate.getForEntity(url, MovieSpaceShipsDto.class);

        // Verificar que la nave existe (respuesta exitosa)
        assertThat("La nave espacial debe existir", response.getStatusCode(), is(HttpStatus.OK));
        assertThat("El body de la respuesta no debe ser nulo", response.getBody(), is(notNullValue()));
    }

    @When("el usuario consulta los detalles de la nave espacial con ID {long}")
    public void elUsuarioConsultaLosDetallesDelUsuarioConID(long shipId) throws Exception {
        log.info("Ejecutado el WHEN - Consultando ship con ID: " + shipId);
        
        // Realizar petición HTTP GET real
        String url = "http://localhost:" + port + "/api/v1/space-ships/" + shipId;
        response = restTemplate.getForEntity(url, MovieSpaceShipsDto.class);
        resultMovieSpaceShipsDto = response.getBody();
    }

    @Then("se muestran los detalles de la nave espacial")
    public void seMuestranLosDetallesDelUsuario() throws JsonProcessingException {
        log.info("Ejecutado el THEN - Verificando detalles de la nave");
        
        // Verificar respuesta HTTP exitosa
        assertThat("La respuesta HTTP debe ser exitosa", response.getStatusCode(), is(HttpStatus.OK));
        
        // Verificar que se devolvieron los detalles
        assertThat("El resultado no debe ser nulo", resultMovieSpaceShipsDto, is(notNullValue()));
        assertThat("El ID no debe ser nulo", resultMovieSpaceShipsDto.id(), is(notNullValue()));
        assertThat("El nombre no debe ser nulo", resultMovieSpaceShipsDto.name(), is(notNullValue()));
        assertThat("La película no debe ser nula", resultMovieSpaceShipsDto.movie(), is(notNullValue()));
        
        log.info("Nave encontrada: " + resultMovieSpaceShipsDto.name() +
                          " de la película " + resultMovieSpaceShipsDto.movie());
    }

    @Given("no existe una nave espacial con el ID {long}")
    public void noExistSpaceShipById(long shipId) throws Exception {
        log.info("Ejecutado el GIVEN - Verificando que NO existe ship con ID: " + shipId);
        // Este step simplemente documenta que esperamos que la nave no exista
        // La verificación real se hace en el WHEN/THEN
    }

    @Then("se muestra un error de nave no encontrada")
    public void seMuestraUnErrorDeNaveNoEncontrada() {
        log.info("Ejecutado el THEN - Verificando error 404");
        
        // Verificar que se devuelve un error 404 (Not Found)
        assertThat("Debe devolver error 404 para nave inexistente", 
                   response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}