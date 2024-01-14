import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import Toast from 'react-native-toast-message';
import Header from './header';
import SendSMS from 'react-native-sms';

export default function App() {
  const [ip, setIp] = useState('');
  const [port, setPort] = useState('');
  const [isConnectionOpen, setIsConnectionOpen] = useState(false);
  const [isConnecting, setIsConnecting] = useState(false);

  //enviar mensagem de texto
  /*const enviarMensagem = () => {
    SendSMS.send({
      body: 'Corpo da mensagem',
      recipients: ['877067492'],  // Números de telefone de destino
      successTypes: ['sent', 'queued'],
      allowAndroidSendWithoutReadPermission: true,
    }, (completed, cancelled, error) => {
      console.log('Mensagem enviada:', completed);
      console.log('Mensagem cancelada:', cancelled);
      console.log('Erro no envio:', error);
    });
  };*/
  


  const handleEnviar = () => {
    if (!ip || !port) {
      Toast.show({
        type: 'error',
        text1: 'Dados inválidos',
        text2: 'Informe o IP e a porta corretamente.',
        visibilityTime: 2000,
      });
      return; // Encerra a execução da função se os dados forem inválidos
    }
  
    // Validar se a porta é um número
    const portNumber = parseInt(port, 10);
    if (isNaN(portNumber) || portNumber <= 0 || portNumber > 65535) {
      Toast.show({
        type: 'error',
        text1: 'Porta inválida',
        text2: 'Informe uma porta válida (1-65535).',
        visibilityTime: 2000,
      });
      return; // Encerra a execução da função se a porta for inválida
    }
  

    try {


      setIsConnecting(true);
      const clientSocket = new WebSocket(`ws://${ip}:${port}`);
      
      // Adicionar um listener para o evento de conexão
      clientSocket.onopen = () => {
        setIsConnectionOpen(true);
        setIsConnecting(false);

        Toast.show({
          type: 'success',
          text1: 'Conexão estabelecida',
          text2: `Conectado a ${ip}:${port}`,
          visibilityTime: 2000,
        });
        //clientSocket.send("ola")
      };

      // Adicionar um listener para o evento de recebimento de mensagem
      clientSocket.onmessage = (event) => {
        const receivedData = event.data;
        Toast.show({
          type: 'success',
          text1: 'Mensagem recebida',
          text2: `Conteúdo: ${receivedData}`,
          visibilityTime: 500,
        });
  
      };

      // Adicionar um listener para o evento de fechamento
      clientSocket.onclose = (event) => {
        setIsConnectionOpen(false);
        setIsConnecting(false);

        Toast.show({
          type: 'error',
          text1: 'Conexão encerrada',
          text2: `Código: ${event.code}, Razão: ${event.reason}`,
          visibilityTime: 2000,
        });
      };

      // Adicionar um listener para o evento de erro
      clientSocket.onerror = (error) => {
        setIsConnectionOpen(false);
        setIsConnecting(false);

        Toast.show({
          type: 'error',
          text1: 'Erro na conexão',
          text2: `Erro: ${error.message}`,
          visibilityTime: 2000,
        });
      };
    } catch (error) {
      setIsConnecting(false);
      console.error('Erro ao criar socket:', error.message);
      Toast.show({
        type: 'error',
        text1: 'Erro ao criar socket',
        text2: error.message,
        visibilityTime: 2000,
      });
    }
  };

  return (
    <View style={styles.container}>
      <Header title="My OTP" />
      <Text style={styles.label}>IP</Text>
      <TextInput
        style={styles.input}
        onChangeText={(text) => setIp(text)}
        value={ip}
        placeholder="Digite o IP"
        editable={!isConnectionOpen && !isConnecting} // Desabilitar durante a conexão
      />
      
      <Text style={styles.label}>PORT</Text>
      <TextInput
        style={styles.input}
        onChangeText={(text) => setPort(text)}
        value={port}
        placeholder="Digite a Porta"
        keyboardType="numeric"
        editable={!isConnectionOpen && !isConnecting} // Desabilitar durante a conexão
      />
      
      <Button title="Enviar" onPress={handleEnviar} disabled={isConnectionOpen || isConnecting} />
      
      {isConnectionOpen && (
        <View style={styles.connectionInfo}>
          <Text style={styles.connectionText}>Conectado a {ip}:{port}</Text>
        </View>
      )}
       <Text style={styles.label}>By Magiccode</Text>
      <Toast ref={(ref) => Toast.setRef(ref)} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 16,
  },
  label: {
    fontSize: 16,
    marginBottom: 8,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 16,
    paddingHorizontal: 8,
  },
  connectionInfo: {
    marginTop: 16,
    padding: 8,
    backgroundColor: '#e0e0e0',
    borderRadius: 8,
  },
  connectionText: {
    fontSize: 14,
    color: 'green',
    textAlign: 'center',
  },
});
