#!/bin/bash

# Run the spring boot application
mvn spring-boot:run &

# Sleep for 10 seconds
sleep 10


# Run the vue application
npm run serve --prefix src/frontend &

# Wait for some time
sleep 5

# Open the browser
xdg-open http://localhost:3000