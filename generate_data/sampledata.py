#!/usr/bin/env python
import pika
import sys
import json
import time
from random import randint
connection = pika.BlockingConnection(pika.ConnectionParameters(host='systembus'))
channel = connection.channel()

channel.exchange_declare(exchange='sensors',
                         type='topic')

routing_key = sys.argv[1] # if len(sys.argv) > 2 else 'anonymous.info'
actualdata = randint(0,40)
while True:
	message = dict()
	message['timestamp'] = int(time.time()*1000)
	message['name'] = 'Temperatura dos Narcisos'
	message['room'] = 'masterbath'
	message['data'] = randint(0,40)
	channel.basic_publish(exchange='sensors', routing_key=routing_key, body=json.dumps(message))
	message['name'] = 'Humidade da Cave'
	message['data'] = randint(0,85)
	channel.basic_publish(exchange='sensors', routing_key=routing_key, body=json.dumps(message))
	message['name'] = 'Luminosidade da Estufa'
	message['data'] = randint(100,200)
	channel.basic_publish(exchange='sensors', routing_key=routing_key, body=json.dumps(message))
	message['name'] = 'Sensor de Fumo'
	message['data'] = randint(10,100)
	channel.basic_publish(exchange='sensors', routing_key=routing_key, body=json.dumps(message))
	print(" [x] Sent %r:%r" % (routing_key, json.dumps(message)))
	time.sleep(0.5)
connection.close()