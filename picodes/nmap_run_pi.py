#ssh pi@172.20.10.6
#python3 nmap_run_pi.py 
import sys
import os
import socket
import nmap 

HOST = '127.0.0.1'
PORT = 65129

ip_address = {}
ip = '172.20.10.'
server_ip = None

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
server_socket.bind((HOST,PORT))



for client in range(4,7):
    nm = nmap.PortScanner() 
    print("Scanning ",ip+str(client))
    nm.scan(ip+str(client), '24-100')
    if nm.all_hosts():
        ip_address[ip+str(client)] = nm
        
for ip,nm in ip_address.items():  
    for host in nm.all_hosts():
        for proto in nm[host].all_protocols():
            print('----------')
            print("IP: ",ip)
            print('Protocol : {0}'.format(proto))

            lport = list(nm[host][proto].keys())
            lport.sort()
            for port in lport:
                print('port : {0}\tstate : {1}\tstate : {2}'.format(port, nm[host][proto][port]["state"], nm[host][proto][port]["product"]))
                if "httpd" in nm[host][proto][port]["product"]:
                    server_ip = ip
print("\n\n\n")                    
print("Server Ip: ",server_ip)

# Creating a local server that has the ip address of the HTTPD Apache server

while True:
    server_socket.listen()
    conn, addr = server_socket.accept()
    with conn:
        print("Recieved conn",addr)
        conn.sendall(server_ip.encode('utf-8'))
