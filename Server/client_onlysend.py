import asyncio
import websockets

async def send_messages(uri):
    async with websockets.connect(uri) as websocket:
        while True:
            message = input("Enter message to send: ")
            await websocket.send(message)

async def main():
    uri = "ws://localhost:3001"
    await send_messages(uri)

if __name__ == "__main__":
    asyncio.run(main())
