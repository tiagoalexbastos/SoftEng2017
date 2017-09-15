import base64
import time
import json
import sys
import random
msg = dict()
msg['timestamp'] = int(time.time()*1000)
msg['data'] = random.randint(0, 40)
msg['name'] = 'Temperatura dos Narcisos'
msg['room'] = 'masterbath'

finaldata = base64.urlsafe_b64encode(json.dumps(msg))

sys.stdout.write(finaldata)
