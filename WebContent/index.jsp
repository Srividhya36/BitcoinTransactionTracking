<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
cntr=0;
time=0;
isset=false;
id=0;
function func()
{

	var btcs = new WebSocket('wss://ws.blockchain.info/inv');

	btcs.onopen = function()

	    {
		    alert("Hello");
		    btcs.send( JSON.stringify( {"op":"unconfirmed_sub"} ) );
	    
	    };


	btcs.onmessage = function(onmsg)
	{
		var resp = JSON.parse(onmsg.data);
		var amount = resp.x.out[0].value+resp.x.out[1].value;
		console.log(amount);
		var hash = resp.x.hash;
		var size = resp.x.size;
	    window.cntr=window.cntr+1;
	    alert(cntr);
// 		$.ajax({
// 	 	    type: "GET",
// 	 	    url: "KafkaProducerServ",
// 	 	    data: {amount:amount, hash:hash, size : size},
// 	 	    success: function(){
// 	  	        alert("hi");
// 	 	    },
// 	 	    error:function(jqxhr,status,exception){
// 	 	    	alert("error"+exception);
// 	 	    }
	 	    
// 	 	});
	    if(id==0)
	    id = setInterval(callserv(),60*1000);
	    setInterval(timereset(),3600000);
	 }
}
function callserv()
{
// 	alert("In scope")
	var cntr1=cntr;
	var time1=time+1;
	time=time1;
	$.ajax({
 	    type: "GET",
 	    url: "KafkaProducerServlet",
 	    data: {cntr :cntr1, time :time1},
 	    success: function(){
  	        alert("hi");
 	      	window.id=0;
 	      	window.cntr=0;
 	    },
 	    error:function(){
 	    	alert("error");
 	    }
 	    
 	});
}
function timereset()
{
	time=0;
}
</script>
<title>Home Page</title>
</head>
<body>
<input type="button" onclick="func()">
Click Here
</body>
</html>
