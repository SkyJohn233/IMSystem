#Protobuf 

Datalength(32bit)+Message(json)

## Message

json

- client -> server

{'code': xx,'data':{}}

Login {userName,sex}
EnterRoom  {roomId}
Send {sendMessage,roomId} 

- server -> client

ListRoom { roomId:[] roomName:[] }

