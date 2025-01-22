const express = require("express");

const app = express();

const port = process.env.PORT || 3000;

const message = process.env.MESSAGE || "HELLO WORLD!";

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
})

app.get("/", (req, res) => {
  res.send(message);
});

