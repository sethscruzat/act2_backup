const express = require('express');
const mongoose = require('mongoose');
const userRoutes = require('./routes/users');

const app = express();

app.use(express.json());

const PORT = 3000;
const local_url = "mongodb://127.0.0.1:27017/android"
mongoose.connect(local_url, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    console.log('Connected to MongoDB');
    app.use('', userRoutes)
    app.listen(PORT, () => {
      console.log(`Server is running on http://localhost:${PORT}`);
    });
  })
  .catch(err => console.error('Error connecting to MongoDB:', err));
