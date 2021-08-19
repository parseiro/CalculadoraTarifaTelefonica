Este é um simulador de preço de ligações telefônicas, ele compara o custo **sem** um plano de chamadas e **com** um plano de chamadas.

Para ver o projeto online no Heroku: **https://telefone-simulador.herokuapp.com/planos/iniciarSimulador**

Para executar o aplicativo localmente: mvnw.cmd spring-boot:run    Acessar: **http://localhost:8080/planos/iniciarSimulador**

Os valores da tarifa básica são definidos no arquivo PrecosTarifaBasica.java

A simulação só é possível fazer entre os DDDs cadastrados neste arquivo.

Por exemplo, é possível simular de 011 para 016, mas não de 016 para 017 (ele vai gerar uma exceção e não haverá resposta no browser - não
foi configurada resposta de erro).


Para executar os testes: mvnw.cmd test


Leonardo
