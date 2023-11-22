const express = require('express');
const User = require('./models/user');

const router = express.Router();

router.get('/users', async (req, res) => {
  try {
    const users = await User.find();
    res.status(200).send(JSON.stringify(users))
    //res.json(users);
  } catch (error) {
    console.error('Error fetching users:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

router.get('/users/:id', async (req, res) => {
  try {
    const userID = {id: req.params.id}

    const user = await User.find(userID);
    res.status(200).send(JSON.stringify(user))
    //res.json(user);
  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

router.post('/login', async (req, res) => {
  const query = {
    password: req.body.password,
    email: req.body.email
  }

  try {
    const user = await User.findOne(query)
    if (user != null){
      const objToSend = {id: user.id};
      res.status(200).send(JSON.stringify(objToSend));
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
