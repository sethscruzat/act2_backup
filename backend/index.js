const express = require('express');
const axios = require('axios');

app = express()
app.use(express.json());
const PORT = 3000;

app.get('/users', async (req, res) => {
  try {
    const users = await axios.get('https://www.jsonkeeper.com/b/HSR4')
    const response = users.data
    res.status(200).send(response);
  } catch (error) {
    console.error('Error fetching users:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.get('/users/:id', async (req, res) => {
  try {
    const userId = req.params.id

    const response = await axios.get('https://www.jsonkeeper.com/b/HSR4');

    if (!Array.isArray(response.data)) {
      res.status(500).json({ error: 'Invalid response format' });
      return;
    }

    const user = response.data.find(user => user.id === parseInt(userId, 10));
    if (!user) {
      res.status(404).json({ error: 'User not found' });
      return;
    }

    res.status(200).send(JSON.stringify(user))
    //res.json(user);
  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.post('/login', async (req, res) => {
  const {email = '', password = ''} = req.body

  if (!email || !password) {
    return res.status(404).json({ error: 'Email and password are required' });
  }

  try {
    const response = await axios.get('https://www.jsonkeeper.com/b/HSR4');
    if (!Array.isArray(response.data)) {
      res.status(500).json({ error: 'Invalid response format' });
      return;
    }

    const user = response.data.find(
      user => user.email === email && user.password === password
    );

    if (!user) {
      res.status(404).json({ error: 'Invalid username or password' });
      return;
    }

    const objToSend = {id: user.id};
    res.status(200).json(objToSend)
  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});

