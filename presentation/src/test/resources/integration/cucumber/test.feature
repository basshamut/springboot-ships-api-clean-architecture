Feature: Consulta de naves espaciales
  Como usuario
  Quiero poder consultar los detalles de una nave espacial
  Para obtener información sobre las naves espaciales registradas

  Scenario: Consultar una nave espacial existente
    Given existe una nave espacial con el ID 1
    When el usuario consulta los detalles de la nave espacial con ID 1
    Then se muestran los detalles de la nave espacial

  Scenario: Consultar una nave espacial inexistente
    Given no existe una nave espacial con el ID 999
    When el usuario consulta los detalles de la nave espacial con ID 999
    Then se muestra un error de nave no encontrada
