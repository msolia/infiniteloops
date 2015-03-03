var express = require('express');
var router = express.Router();

var xml = require('xml');

var mongoose = require('mongoose');
var Todo = require('../models/model.js');
var monk = require('monk');

/* GET /todos listing. */
router.get('/', function(req, res, next) {
  Todo.find(function (err, todos) {
    if (err) return next(err);
    res.json(todos);
  });
});

//find by id
router.get('/:id', function(req, res, next) {
  Todo.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

//find by username
router.get('/users/:username', function (req, res,next) {
  Todo.find({'username':req.params.username}, function (err, todos) {
    if (err) return next(err);
	console.log("get")
	var jsonString = JSON.stringify(todos);
	//res.json(todos);
	res.send('{'+'"result":'+jsonString +'}');
  });
});


router.route('/users/:username/:password/:gender/:age').post(function(req, res) {
  
    var userName = req.params.username;
    var userPassword = req.params.password;
	var userGender = req.params.gender;
	var userAge = req.params.age;
	
	var db = monk('admin:admin@ds047901.mongolab.com:47901/demoapps');
    // Set our collection
    var collection = db.get('users');
	
    // Submit to the DB
    collection.insert({
        "username" : userName,
        "password" : userPassword,
		"gender" : userGender,
		"age" : userAge
    });
	
	res.send("Added!!")
});

module.exports = router;
