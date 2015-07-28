//
//  HomeViewController.swift
//  LoginForm
//
//  Created by Test on 02/03/15.
//  Copyright (c) 2015 LBi. All rights reserved.
//

import Foundation
import UIKit

class HomeViewController: UIViewController
{

    @IBOutlet var welcomemsg: UILabel!
    
    var recievedString: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        welcomemsg.text = "Hi \(recievedString)"
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
