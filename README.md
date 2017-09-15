# SoftEng2017 - Domotics

Software Engineering Project

## Introduction

Domotics (from the latin word “domus”, house), it’s the encounter of information technology, electrotechnics and electronics that makes a home become “smart”.

It’s the tool that allows to control systems, devices and automations with the aim of increase the living and comfort quality of the domestic space, but not only.

The building starts to develop its “own intelligence”, characterised not by the amount of high technology it contains, but by the way in which the technologies integration is projected and by how these technologies are able to satisfy the individuals’ needs, which are always in evolution.

## Project Description

Our project is based in sensors that provide information about temperature, humidity, light, and others cases. The information from each sensor is given to the broker and its passed to the other points of the system. The database will store the information coming from the broker and the dashboard will consume it in real time, to provide information to the user in graph's form. The dashboard can communicate with the database in order to get information about the sensor's history. There are alerts on the dashboard that will appear if any value is above/below a certain threshold.

Our components are created by a docker compose in order to establish the network layer links between the containers. Then, all our components are automatic deployed by Jenkins after the tests are passed.



For more information: https://xcoa.av.it.pt/~es2016-2017_g202/
