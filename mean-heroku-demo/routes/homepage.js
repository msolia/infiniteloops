
var express = require("express");
var router = express.Router();

app.get("/",function(req,res){
    res.render("homepage",{title:"Homepage"});
    console.log("homepage.js");
});


module.exports = router;