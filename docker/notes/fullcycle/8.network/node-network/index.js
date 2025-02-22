const mongoose = require("mongoose");

//Usando nome do container "db"!
mongoose.connect("mongodb://db:27017/test", { })
    .then(() => console.log("Connected to mongo db"))
    .catch((err) => console.log(err));