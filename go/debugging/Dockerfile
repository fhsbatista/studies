FROM golang:1.23

WORKDIR go/src

COPY ./go.mod ./go.sum ./

RUN go mod download

RUN go install github.com/go-delve/delve/cmd/dlv@latest

COPY . .

RUN go build -gcflags="all=-N -l" main.go

ENTRYPOINT ["./main"]