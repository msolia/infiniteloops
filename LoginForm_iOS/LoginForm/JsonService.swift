import foundation


class JsonService : NSObject, NSURLConnectionDelegate
{
    let urlString: String
    var responseData: NSMutableData = NSMutableData()
    var responseMessage: NSURLResponse = NSURLResponse()
    
    init(urlString: String) {
        self.urlString = urlString.stringByAddingPercentEscapesUsingEncoding(NSUTF8StringEncoding);
    }
    
    func startDownloaderWithUrl() {
        
        let url:NSURL = NSURL.URLWithString(self.urlString);
        let r:NSURLRequest = NSURLRequest(URL: url);
        let connection:NSURLConnection = NSURLConnection(
            request: r,
            delegate: self,
            startImmediately: true);
        
    }
    
    func connection(connection: NSURLConnection!, didFailWithError error: NSError!) {
        NSLog("Connection failed.\(error.localizedDescription)")
    }
    
    func connection(connection: NSURLConnection, didRecieveResponse response: NSURLResponse)  {
        NSLog("Recieved response")
        self.responseMessage = response;
    }
    
    
    func connection(didReceiveResponse: NSURLConnection!, didReceiveResponse response: NSURLResponse!) {
        
        self.responseData = NSMutableData()
    }
    
    func connection(connection: NSURLConnection!, didReceiveData data: NSData!) {
        
        self.responseData.appendData(data)
    }
    
    func connectionDidFinishLoading(connection: NSURLConnection!) {
        
        let responseString: NSString = NSString(data: self.responseData, encoding: NSUTF8StringEncoding);
        
        NSLog("%@", "Finished Loading");
        //NSLog("%@", self.responseData);
        NSLog("%@", responseString);
        
    }
}