<?php
	
	$connection = mysqli_connect("test.c4lshtnnkzfe.us-east-1.rds.amazonaws.com:3306","root","rootroot","test") or die("Error " . mysqli_error($connection));
	$id = $_POST["id"];
	$entities = $_POST['entities'];
	$size = count($entities);
	$emparray = array();

    for($i=0; $i<$size; $i++) {
		$entity = $entities[$i];
		$sql = "select * from tweets where Tweet like '%{$entity}%' and Areaid = {$id}";
    	$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));    	
    	while($row =mysqli_fetch_assoc($result))
    	{
    		if(in_array($row, $emparray)) {    			
                ;
    		}
    		else {
                $emparray[] = array_map('utf8_encode', $row);
    		}
        	
    	}

	}

    mysqli_close($connection);

    echo json_encode($emparray);

?>
