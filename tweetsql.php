<?php
	
	$connection = mysqli_connect("127.0.0.1","root","","test") or die("Error " . mysqli_error($connection));
	$id = $_POST["id"];
	$entities = $_POST['entities'];
	$size = count($entities);
	$emparray = array();

    for($i=0; $i<$size; $i++) {
		$entity = $entities[$i];
		$sql = "(select * from tweets where Tweet like '%{$entity}%' and Areaid = {$id})";
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