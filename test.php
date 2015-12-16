<?php
	
	$connection = mysqli_connect("127.0.0.1","root","","test") or die("Error " . mysqli_error($connection));
	//$id = $_POST["id"];
	//$entities = $_POST['entities'];
	//$size = count($entities);
	$emparray = array();

	/*for($i=0; $i<$size; $i++) {
		$entity = $entities[$i];
		$sql = "select * from tweets where Tweet like '%{$entity}%' and Areaid = {$id}";
		//$sql = "select * from tweets where Tweet like '%{$entity}%'";
		//$sql = "select * from tweets where Tweet like '%{$entity}%'";
    	$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));    	
    	while($row =mysqli_fetch_assoc($result))
    	{
    		if(in_array($row, $emparray)) {
    			;
    		}
    		else {
    			$emparray[] = $row;
    		}
        	
    	}

	}*/
	//$sql = "select * from tweets where Tweet like '%{$entity}%' and Areaid = {$id}";
	//$sql = "select * from tweets where Tweet like '%{$entities[0]}%'";
	//$sql = "select * from tweets where Tweet like '%Williamsburg%'";
	$sql = "select * from tweets where Tweet like '%Williamsburg%'";
		//$sql = "select * from tweets where Tweet like '%{$entity}%'";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));    	
   	while($row =mysqli_fetch_assoc($result))
   	{
   		if(in_array($row, $emparray)) {
   			;
     	}
    	else {
    		$emparray[] = $row;
    	}
        	
    }
    echo json_encode($emparray);
    $size = count($emparray);
    /*for($i = 0; $i<$size; $i++)
        echo $emparray[$i]['Tweet'];*/
	
    mysqli_close($connection);
?>