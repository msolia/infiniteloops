//
//  SignUpViewController.swift
//  LoginForm
//
//  Created by Test on 02/03/15.
//  Copyright (c) 2015 LBi. All rights reserved.
//

import UIKit

class SignUpViewController: UIViewController
    
{
    @IBOutlet weak var username: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var ageLabel: UILabel!
    @IBOutlet weak var genderLabel: UILabel!
    
    @IBOutlet weak var ageSlider: UISlider!
   
    @IBOutlet weak var genderButton: UISwitch!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
       
        
    }
   
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func ageSlider(sender: UISlider) {
        
        var currentValue = Int(sender.value)
        
        ageLabel.text = "\(currentValue)"
    }

    @IBAction func genderToggleButton(sender: UISwitch)
    {
        if genderButton.on
        {
                genderLabel.text = "Male"
            
        }
        else
        {
            genderLabel.text = "Female"
            self.genderButton.setOn(false, animated: true)
        }
        
        
    }
    
    @IBAction func signUp(sender: AnyObject)
    {
        var userName:String = username.text!
        var userPassword:String = password.text!
        var userGender:String  = genderLabel.text!
        var userAge:String = ageLabel.text!
        
        var error: NSError?
        var reponseError: NSError?
        var response: NSURLResponse?
        
        var urlPath = "https://infinite-badlands-1843.herokuapp.com/service/users/\(userName)/\(userPassword)/\(userGender)/\(userAge)"
        var url: NSURL = NSURL(string: urlPath)!
        
        var request = NSMutableURLRequest(URL: url)
        var session = NSURLSession.sharedSession()
        request.HTTPMethod = "POST"
        var urlData: NSData? = NSURLConnection.sendSynchronousRequest(request, returningResponse:&response, error:&reponseError)
        
        if(urlData != nil)
        {
            let secondViewController = self.storyboard?.instantiateViewControllerWithIdentifier("login") as ViewController
            
            secondViewController.userCreatedMsg = "User Created!"
            
            self.presentViewController(secondViewController, animated: true, completion: nil)
            
        }
        
      
    }
    
    
}
