import asyncio
import websockets

async def handler(websocket, path):
    try:
        print(f"Client connected: {websocket.remote_address}")

        while True:
            #data = await websocket.recv()
            #print(f"Data received: {data}")

            # Envie uma mensagem constante para o cliente
            await websocket.send(input(">>"))

            await asyncio.sleep(1)  # Espera 1 segundo antes de enviar a próxima mensagem

    except websockets.exceptions.ConnectionClosed:
        print(f"Connection closed: {websocket.remote_address}")

start_server = websockets.serve(handler, "0.0.0.0", 9001)
print("websocketServer is Up")

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()

