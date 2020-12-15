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
    
ip = "http://"+server_ip
    
urlflr1 =ip+'/Androids/v1/floor1val.php'
urlflr2 = ip+'/Androids/v1/floor2val.php'
urllocks = ip+'/Androids/v1/locksval.php'
urlsecurity = ip+'/Androids/v1/securityval.php'
urlthermostat = ip+'/Androids/v1/thermostatval.php'
urlgaragedoorval = ip+'/Androids/v1/garagedoorval.php'

while(True):
    
    rfloor1val = requests.post(urlflr1,).json()
    rfloor2val = requests.post(urlflr2,).json()
    rlocks = requests.post(urllocks,).json()
    rsecurity = requests.post(urlsecurity,).json()
    rthermostat = requests.post(urlthermostat,).json()
    rgaragedoorval = requests.post(urlgaragedoorval,).json()
    
    #message = rfloor1val["message"]
    #print(message['light1'])
    print(rfloor1val)
    print(rfloor2val)
    print(rlocks)
    print(rsecurity)
    print(rthermostat)
    print(rgaragedoorval)
    #print(rfloorval)
    exit(0)



 
    

