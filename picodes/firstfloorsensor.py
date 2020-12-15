#python3 firstfloorsensor.py 1 1

#import the GPIO and time package
import RPi.GPIO as GPIO
import time
import requests
import json
import sys
import socket
import signal

    
server_ip = None
HOST = '127.0.0.1'
PORT = 65129

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST,PORT))    
    server_ip = s.recv(1024).decode('utf-8')
    
print(server_ip)
#print(sys.argv)
if len(sys.argv) < 3:
	print("Use python3 filename.py 0,1")
	print("first argument corresponds to sensor0")
	print("second argument corresponds to sensor1")
	exit(0)
	
a=int(sys.argv[1])
b=int(sys.argv[2])

ip = "http://"+server_ip
url = ip+'/Androids/v1/floor1sensor.php'

payload = {'sensor1' : a, 'sensor2' : b}
r = requests.post(url,data = payload)
print(r.text)
