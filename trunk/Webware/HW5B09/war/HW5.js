// Homework 5, CS4741-B09
// Author: Jason Codding & Eric Greer
function initialize()
{
//	Intentionally left blank
}

function showPicture(urlBase, pic)
{
	var req = new XMLHttpRequest();
  // Set the handler function to receive callback notifications
  // from the request object

  req.onreadystatechange = getReadyStateHandler(req, ReallyShowPicture);

  // Open an HTTP POST connection to the shopping cart servlet.
  // Third parameter specifies request is asynchronous.
  req.open("POST", "picture", true);

  // Specify that the body of the request contains form data
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  // Send form encoded data stating that I want to add the 
  // specified item to the cart.
  req.send("pic="+pic);

	
	
}


/*
 * Returns a function that waits for the specified XMLHttpRequest
 * to complete, then passes its XML response
 * to the given handler function.
 * req - The XMLHttpRequest whose state is changing
 * responseXmlHandler - Function to pass the XML response to
 *
 * From: http://www.ibm.com/developerworks/library/j-ajax1/
 */
function getReadyStateHandler(req, responseXmlHandler) {

  // Return an anonymous function that listens to the 
  // XMLHttpRequest instance
  return function () {

    // If the request's status is "complete"
    if (req.readyState == 4) {
      
      // Check that a successful server response was received
      if (req.status == 200) {

        // Pass the XML payload of the response to the 
        // handler function
        responseXmlHandler(req.responseXML);

      } else {

        // An HTTP problem has occurred
        alert("HTTP error: "+req.status);
      }
    }
  }
}

/*
*		Handle's modifying the HTML DOM with the retrieved picture
*/
function ReallyShowPicture(pictureXML){
	var picture = pictureXML.getElementsByTagName("picture")[0];
	var url = picture.getAttribute("url");
 
	var mainPic = document.getElementById("mainPic");
	var picImg = mainPic.getElementsByTagName("img")[0];
		
	picImg.src = url;
}

