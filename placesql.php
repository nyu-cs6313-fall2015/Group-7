<?php
    //open connection to mysql db
    $connection = mysqli_connect("test.c4lshtnnkzfe.us-east-1.rds.amazonaws.com:3306","root","root","test") or die("Error " . mysqli_error($connection));

    //fetch table rows from mysql db
    $sql = "select * from keyword";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = array_map('utf8_encode', $row);
    }    

    //close the db connection<meta http-equiv="refresh" content="3" />
    mysqli_close($connection);
    echo json_encode($emparray);
?>