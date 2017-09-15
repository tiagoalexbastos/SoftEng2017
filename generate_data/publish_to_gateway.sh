x=$(python generate_for_curl.py)

curl --data "data=$x" localhost:12214/SensorGateway/publish
