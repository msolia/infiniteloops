var express = require('express');
var router = express.Router();

var xml = require('xml');

var mongoose = require('mongoose');
var Todo = require('../models/model.js');

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
	
	var jsonString = JSON.stringify(todos);
	//res.json(todos);
	res.send('{'+'"result":'+jsonString +'}');
  });
});


module.exports = router;
