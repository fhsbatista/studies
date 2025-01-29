FROM golang:latest AS builder

LABEL env="production"

WORKDIR /app
COPY . .

RUN CGO_ENABLED=0 go build -o server main.go

ENTRYPOINT ["./server"]
CMD ["./server"]
CMD ["ls", "-la"]