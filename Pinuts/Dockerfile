FROM python:alpine

WORKDIR /usr/src/app

COPY ./p1/alarm.py /usr/src/app/

RUN apk --update add py-pip
RUN pip install pika

