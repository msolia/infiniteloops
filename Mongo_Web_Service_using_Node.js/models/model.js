var mongoose = require('mongoose');

var MSchema = new mongoose.Schema({
  username: String,
  password: String,
 
});

module.exports = mongoose.model('users', MSchema);
