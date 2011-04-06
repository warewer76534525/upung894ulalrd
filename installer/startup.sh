#!/bin/bash
java -jar bin/confirmwatcher-service/confirmwatcher-service.jar &
java -jar bin/ftpfilesender-service/ftpfilesender-service.jar &
java -jar bin/housekeeping-service/housekeeping-service.jar &
java -jar bin/mailsender-service/mailsender-service.jar &
java -jar bin/sapfilewatcher-service/sapfilewatcher-service.jar &
