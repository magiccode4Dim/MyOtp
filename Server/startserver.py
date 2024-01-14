import socket
import time

SERVER_IP = '0.0.0.0'  
SERVER_PORT = 9001

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((SERVER_IP, SERVER_PORT))
    s.listen()
    print("listening...")
    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        data = conn.recv(1024)
        print(data)
        while True:
            try:
                # Envie uma mensagem constante para o cliente
                conn.sendall("Mensagem do servidor".encode('utf-8'))
                time.sleep(1)  # Espera 1 segundo antes de enviar a próxima mensagem
            except KeyboardInterrupt:
                break
