var swig = require("swig");
var express = require("express");
var app = express();// Web framework to handle routing requests
var consolidate = require("consolidate"); // Templating library adapter for Express

// view engine setup
app.set("views","./views");
app.set("view engine","html");
app.engine("html",consolidate.swig);

var routes = require('./routes');
routes(app);

var listener = app.listen("8099");
console.log("Started Express server at:"+listener.address().port);