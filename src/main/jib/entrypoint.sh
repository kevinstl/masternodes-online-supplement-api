#!/bin/sh

echo "The application will start in ${JHIPSTER_SLEEP}s..." && sleep ${JHIPSTER_SLEEP}
<<<<<<< HEAD
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.cryptocurrencyservices.microservice.JxJhipsterMicroserviceTemplateApp"  "$@"
=======
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.cryptocurrencyservices.masternodessuplement.api.MasternodesOnlineSupplementApiApp"  "$@"
>>>>>>> 0284cc9f6f3785d76bb0b3ca255ac2b96b28461d
