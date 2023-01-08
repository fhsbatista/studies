const router = require('express').Router()
const Person = require('../models/Person')

//create
router.post('/', async (req, res) => {
  const { name, salary, approved } = req.body
  const person = { name, salary, approved }

  if (!name) {
    res.status(422).json({ error: 'O nome é obrigatório' })
    return
  }

  if (!salary) {
    res.status(422).json({ error: 'O salário é obrigatório' })
    return
  }

  if (!approved) {
    res.status(422).json({ error: 'O status de aprovação é obrigatório' })
    return
  }

  try {
    await Person.create(person)
    res.status(201).json({ message: 'Pessoa inserida com sucesso!' })
  } catch (error) {
    res.status(500).json({ error: error })
  }
})

//read
router.get('/', async (req, res) => {
  console.log(req)
  try {
    const people = await Person.find()
    res.status(200).json(people)
  } catch (error) {
    res.status(500).json({ error: error })
  }
})

router.get('/:id', async (req, res) => {
  try {
    const id = req.params.id
    const person = await Person.findOne({ _id: id })
    if (!person) {
      res.status(422).json({ error: 'Pessoa não encontrada' })
      return
    }
    res.status(200).json(person)
  } catch (error) {
    res.status(500).json({ error: error })
  }
})

//update (put and patch)
router.patch('/:id', async (req, res) => {
  const id = req.params.id
  const { name, salary, approved } = req.body
  const person = {
    name,
    salary,
    approved,
  }
  try {
    const updatedPerson = await Person.updateOne({ _id: id }, person)
    if (updatedPerson.matchedCount === 0) {
      res.status(422).json({ error: 'Pessoa não encontrada' })
      return
    }
    if (updatedPerson.modifiedCount === 0) {
      res.status(422).json({ error: 'Nenhum dado foi atualizado' })
      return
    }
    res.status(200).json(person)
  } catch (error) {
    res.status(500).json({ error: error })
  }
})

//delete
router.delete('/:id', async (req, res) => {
  const id = req.params.id
  try {
    const person = await Person.findOne({ _id: id })
    if (!person) {
      res.status(422).json({ error: 'Pessoa não encontrada' })
      return
    }
    await Person.deleteOne({ _id: id })
    res.status(200).json({ message: 'Pessoa apagada com sucesso' })
    return
  } catch (error) {
    res.status(500).json({ error: error })
  }

})


module.exports = router