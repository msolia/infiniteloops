module.exports = exports = function(app)
{
    //Homepage
    app.get("/",function(req,res){
     res.render("homepage",{title:"Homepage"});
     console.log("homepage.js");
    });

    
    //404
    app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    console.log("wrongpage:"+req.url);
    res.render("error",{error:"Resource not found"});
    });
          
}