from simple_websocket_server import WebSocketServer, WebSocket


class MyOtpServer(WebSocket):
    def handle(self):
        for client in clients:
            if client != self:
                #para ser um chat 
                client.send_message(self.data)

    def connected(self):
        print(self.address, 'connected')
        for client in clients:
            client.send_message(self.address[0] + u' - connected')
        clients.append(self)

    def handle_close(self):
        clients.remove(self)
        print(self.address, 'closed')
        for client in clients:
            client.send_message(self.address[0] + u' - disconnected')


clients = []

server = WebSocketServer('0.0.0.0', 3001,  MyOtpServer)
server.serve_forever()