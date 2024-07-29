# MyOtp
O myotp server possibilita o envio de mensagens OTP vindas de uma determinada aplicação para clientes na rede GSM/3G/4G/5G, utilizando um Smarthphone Android como transmissor.
O myotp é um servidor websockets simples que possibilita a conecção de transmissores e receptores. Os receptores são os dispositivos que apenas recebem dados do servidor websocket, enquanto os transmissões apenas enviam as informações que devem ser transmitidas. Neste contexto, a aplicação web é um transmissor e o smartphone é um receptor que recebe as informações transmitidas e envia na rede movel.
![myotp](https://github.com/user-attachments/assets/b290bafd-f1c9-47a6-a61d-1de5d0f8f5f7)

O projecto Client_AS, é o projecto de android Studio que deve ser compilado e instalado em um smart phone com capacidade de enviar mensagens de texto.
![Screenshot_20240728_135504](https://github.com/user-attachments/assets/5fb438ee-d4cb-4357-9f67-fb726ef68175)

A aplicação android, precisa de permissão de para enviar mensagens de texto. Assim que concedida a permissão, a aplicação pode se conectar com o servidor websockets (O servidor e smart phone devem estar na mesma rede).

## Rodando o servidor websockets
Para rodar o servidor, precisará de ter o python3 instalado com as respectivas dependências do projecto. Após instalar  basta somente rodar o arquivo myOTPserver.py (Para questões de testes podemos também rodar os demais ficheiros client*..py para simular transmisores e receptores).
![Screenshot from 2024-07-29 13-25-48](https://github.com/user-attachments/assets/83024aba-aba8-4c1e-8e5b-947f68d06eac)

## Integrando o servidor myotp com uma aplicação escrita em Python
![Screenshot from 2024-07-29 14-05-28](https://github.com/user-attachments/assets/ec4054bf-5485-4090-8efc-bf8add59342f)

A mensagem deve ser apresentada na notação:{"cell": "phone number", "message": "content message"} 
Exemplo: {"cell": "+258840000000", "message": "o seu codigo OPT é 2024"}

## NOTA IMPORTANTE
O MYOTP SERVER FOI DESENVOLVIDO PARA FINS DE TESTES, NÃO RECOMENDA-SE O SEU USO EM PRODUÇÃO.

