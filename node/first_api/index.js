// initial setup
const express = require('express')
const mongoose = require('mongoose')
const app = express()

// json reading setup
app.use(express.urlencoded({ extended: true }))
app.use(express.json())

// routes
const personRoutes = require('./routes/person_routes')
app.use('/person', personRoutes)

// initial route
app.get('/', (req, res) => {
  res.json({ message: 'Oi Express!' })
})

//mongoose
const DB_USER = 'fbatista'
const DB_PASSWORD = encodeURIComponent('235711')

mongoose
  .connect(`mongodb+srv://${DB_USER}:${DB_PASSWORD}@cluster0.tvthhg2.mongodb.net/bancofirstapi?retryWrites=true&w=majority`)
  .then(() => {
    // set port
    app.listen(3000)
    console.log('mongo conectado')
  })
  .catch((error) => {
    console.log(error)
  }) 