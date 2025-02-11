FROM golang:1.23-alpine

WORKDIR /app
EXPOSE 80

COPY main.go ./
ENV GO111MODULE=off
RUN go build -o main-app .

CMD [ "./main-app" ]