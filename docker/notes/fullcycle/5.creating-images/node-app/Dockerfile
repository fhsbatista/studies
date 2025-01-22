ARG NODE_VERSION=latest
FROM node:${NODE_VERSION}

ENV PORT=3001
ENV MESSAGE="Hello docker."

# RUN useradd -m mynode
# USER mynode

USER 1001

WORKDIR /app

COPY . .

CMD ["node", "index.js"]