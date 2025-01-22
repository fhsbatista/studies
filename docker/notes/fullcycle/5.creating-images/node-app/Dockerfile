ARG NODE_VERSION=latest
FROM node:${NODE_VERSION}

ENV PORT=3001
ENV MESSAGE="Hello docker."

WORKDIR /app

COPY . .

CMD ["node", "index.js"]