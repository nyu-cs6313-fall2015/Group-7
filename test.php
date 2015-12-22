<?php
	
	$connection = mysqli_connect("test.c4lshtnnkzfe.us-east-1.rds.amazonaws.com:3306","root","rootroot","test") or die("Error " . mysqli_error($connection));
	if(!$connection) echo "fail"; 
    else echo "success"; 
?>
