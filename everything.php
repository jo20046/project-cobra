<?php

	$pdo = new PDO('mysql:host=localhost;dbname=quizapp;charset=utf8','root','bocholt');
	
	$sql = "SELECT * FROM everything";
	
	
	
	foreach($pdo->query($sql) as $row){
		$quote[]=$row['quote'];
		$person[]=$row['person'];
		$category[]=$row['category'];
	}
		
	for($temp = 0; $temp < sizeof($quote); $temp++){
		echo $quote[$temp].";";
		
		echo $quote[$temp].";";
		
		echo $quote[$temp]."|";
	}
	
	
?>
