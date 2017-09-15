#!/usr/bin/env python
import pika
import sys
import json
import time

done = False
while not done:
    try:
        connection = pika.BlockingConnection(pika.ConnectionParameters(host='systembus'))
        done = True
    except:
        done = False
        time.sleep(2)
channel = connection.channel()

channel.exchange_declare(exchange='sensors', type='topic')
result = channel.queue_declare(exclusive=True)
queue_name = result.method.queue

binding_keys = ["realtime.sensordata"] #sys.argv[1:]
if not binding_keys:
    sys.stderr.write("Usage: %s [binding_key]...\n" % sys.argv[0])
    sys.exit(1)

for binding_key in binding_keys:
    channel.queue_bind(exchange='sensors',
                       queue=queue_name,
                       routing_key=binding_key)

print(' [*] Waiting for messages. To exit press CTRL+C')

def callback(ch, method, properties, body):
    print(" [x] %r:%r" % (method.routing_key, body))
    msg_in = json.loads(body)

    if msg_in['name'] == 'Temperatura dos Narcisos' and msg_in['data'] > 39:
        msg_out = json.dumps({'timestamp': int(time.time()*1000) , 'message': 'Temperature is over 39 degrees.' , 'type': 'warning', 'title': 'Temperatura dos Narcisos'})
        channel.basic_publish(exchange='sensors', routing_key="realtime.alarms", body= msg_out)
    elif msg_in['name'] == 'Humidade da Cave' and msg_in['data'] > 83:
        msg_out = json.dumps({'timestamp': int(time.time()*1000) , 'message': 'Humidity is over 83 percent.' , 'type': 'warning', 'title': 'Humidade da Cave'})
        channel.basic_publish(exchange='sensors', routing_key="realtime.alarms", body= msg_out)
    elif msg_in['name'] == 'Luminosidade da Estufa' and msg_in['data'] > 195:
        msg_out = json.dumps({'timestamp': int(time.time()*1000) , 'message': 'Luminosity is over 195 lux.' , 'type': 'warning', 'title': 'Luminosidade da Estufa'})
        channel.basic_publish(exchange='sensors', routing_key="realtime.alarms", body= msg_out)
    elif msg_in['name'] == 'Sensor de Fumo' and msg_in['data'] > 98:
        msg_out = json.dumps({'timestamp': int(time.time()*1000) , 'message': 'Concentration is over 98 ppm.' , 'type': 'warning', 'title': 'Sensor de Fumo'})
        channel.basic_publish(exchange='sensors', routing_key="realtime.alarms", body= msg_out)
		
        
		
channel.basic_consume(callback,
                      queue=queue_name,
                      no_ack=True)

channel.start_consuming()
