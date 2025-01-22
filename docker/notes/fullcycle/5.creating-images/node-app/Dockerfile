ARG NODE_VERSION=latest
FROM node:${NODE_VERSION}

ENV PORT=3001
ENV MESSAGE="Hello docker."

# RUN useradd -m mynode
# USER mynode

USER 1001

WORKDIR /app

COPY . .

HEALTHCHECK --interval=10s --timeout=5s --start-period=5s --retries=3 \
  CMD ["curl", "-f", "http://localhost:3001"]

CMD ["node", "index.js"]