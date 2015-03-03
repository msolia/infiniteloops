//
//  ViewController.swift
//  LoginForm
//
//  Created by Test on 27/02/15.
//  Copyright (c) 2015 LBi. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var nameTextField: UITextField!
    
    @IBOutlet weak var passwordLabel: UILabel!
    
    @IBOutlet weak var passwordTextField: UITextField!
    
    @IBOutlet weak var submit: UIButton!
    
    @IBOutlet weak var msg: UILabel!
    
    var userCreatedMsg: String = ""
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        if (userCreatedMsg == "User Created!")
        {
            
            
            let myAlert = UIAlertView()
         //   myAlert.title = "Title"
            myAlert.message = userCreatedMsg
            myAlert.addButtonWithTitle("Dismiss")
            myAlert.delegate = self   
            myAlert.show()  
        }
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    func validate (userName : String, password : String) -> Bool
    {
        var error: NSError?
        var reponseError: NSError?
        var response: NSURLResponse?
        
     
        var urlPath = "https://infinite-badlands-1843.herokuapp.com/service/users/"+userName
        var url: NSURL = NSURL(string: urlPath)!
        var request: NSURLRequest = NSURLRequest(URL: url)
        var urlData: NSData? = NSURLConnection.sendSynchronousRequest(request, returningResponse:&response, error:&reponseError)
        
        if ( urlData != nil )
        {
            let res = response as NSHTTPURLResponse!;
            
            if (res.statusCode >= 200 && res.statusCode < 300)
            {
                var responseData:NSString  = NSString(data:urlData!, encoding:NSUTF8StringEncoding)!
                
                
                let jsonData:NSDictionary = NSJSONSerialization.JSONObjectWithData(urlData!, options:NSJSONReadingOptions.MutableContainers , error: &error) as NSDictionary
                
               
                
                let result:NSArray = jsonData.valueForKey("result") as NSArray
                               
                //if not valid urlpath
                if (result.count == 0)
                {
                    return false
                }
               
                if (result[0]["username"] as NSString == userName && result[0]["password"] as NSString == password)
                {
                    
                    return true
                }
                
                else
                {
                    return false
                }
                
            } else
            {
                
                return false
            }
        }
        
        return false
        
    }
    
    @IBAction func softLogin(sender: AnyObject)
    {
        
        var validateCredential: Bool = validate(nameTextField.text,password: passwordTextField.text)
        
        if validateCredential
        {
            msg.text = "Hi \(nameTextField.text)!"
            println("valid user")
            
            let secondViewController = self.storyboard?.instantiateViewControllerWithIdentifier("h") as HomeViewController
            
            //self.navigationController?.pushViewController(secondViewController, animated: true)
            
            secondViewController.recievedString = nameTextField.text
            
            self.presentViewController(secondViewController, animated: true, completion: nil)
    
        }
        else
        {
           msg.text = "Wrong Credentials"
           println("invalid user")
        }
        
        
    }
    
    
    
  /*  override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?)
    {
        var HViewController: HomeViewController = segue.destinationViewController as HomeViewController
        
        HViewController.recievedString = nameTextField.text!
        
    }*/

}

