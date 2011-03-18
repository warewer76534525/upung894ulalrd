#!/bin/bash
cd bin/confirmwatcher-service

java -jar confirmwatcher-service.jar &

cd ../ftpfilesender-service

java -jar ftpfilesender-service.jar &

cd ../housekeeping-service

java -jar housekeeping-service.jar &

cd ../mailsender-service

java -jar mailsender-service.jar &

cd ../sapfilewatcher-service

java -jar sapfilewatcher-service.jar &