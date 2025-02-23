const mongoose = require("mongoose");

//Usando nome do container "mongo"!
mongoose.connect("mongodb://mongo:27017/test", { })
    .then(() => console.log("Connected to mongo db"))
    .catch((err) => console.log(err));