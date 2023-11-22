const express = require('express');
const User = require('./models/user');
const axios = require('axios');

const router = express();

router.get('/users', async (req, res) => {
  try {
    const users = await axios.get('https://www.jsonkeeper.com/b/HSR4')
    res.status(200).send(serializedUsers);
  } catch (error) {
    console.error('Error fetching users:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

router.get('/users/:id', async (req, res) => {
  try {
    const userID = req.params.id

    const response = await axios.get('https://www.jsonkeeper.com/b/HSR4');
    const user = response.data.users.find(user => user.id === parseInt(userID, 10));
    res.status(200).json(user)
    //res.json(user);
  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

router.post('/login', async (req, res) => {
  const {email,password} = req.body

  try {
    const response = await axios.get('https://www.jsonkeeper.com/b/HSR4');
    const user = response.data.users.find((user) => user.email === email && user.password === password );

    if (user != null){
      const objToSend = {id: user.id};
      res.status(200).json(objToSend)
    }

    if (!user) {
      return res.status(404).send();
    }

  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});


module.exports = router;
