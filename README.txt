Para executar o aplicativo Spring:

mvnw.cmd spring-boot:run

Acessar: http://localhost:8080/planos/iniciarSimulador

Os valores da tarifa básica são definidos no arquivo PrecosTarifaBasica.java
A simulação só é possível fazer entre os DDDs cadastrados neste arquivo.
Por exemplo, é possível simular de 011 para 016, mas não de 016 para 017 (ele vai gerar uma exceção e não haverá resposta no browser - não
foi configurada resposta de erro).


Para executar os testes:

mvnw.cmd test


Leonardo Vilela Pinheiro