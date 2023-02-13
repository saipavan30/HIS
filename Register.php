<?php
    $con = mysqli_connect("localhost", "id2817174_his", "hospital", "id2817174_his");
    
    $name = $_POST["name"];
	$username = $_POST["username"];
    $pid = $_POST["pid"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, pid, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssis", $name, $username, $pid, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>