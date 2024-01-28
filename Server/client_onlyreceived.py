import asyncio
import websockets

async def receive_messages(uri):
    async with websockets.connect(uri) as websocket:
        while True:
            message = await websocket.recv()
            print(f"Received message: {message}")

async def main():
    uri = "ws://localhost:3001"
    await receive_messages(uri)

if __name__ == "__main__":
    asyncio.run(main())
