<?php

	$pdo = new PDO('mysql:host=localhost;dbname=quizapp;charset=utf8','root','bocholt');
	
	$sql = "SELECT * FROM everything";
	
	
	
	foreach($pdo->query($sql) as $row){
		$quote[]=$row['quote'];
		$person[]=$row['person'];
		$category[]=$row['category'];
	}
		
	for($temp = 0; $temp < sizeof($quote); $temp++){
		$j_encoded = json_encode(utf8_encode($quote[$temp]));
		$j_decoded = json_decode($j_encoded);
		echo $j_decoded.";";
		
		$j_encoded = json_encode(utf8_encode($person[$temp]));
		$j_decoded = json_decode($j_encoded);
		echo $j_decoded.";";
		
		$j_encoded = json_encode(utf8_encode($category[$temp]));
		$j_decoded = json_decode($j_encoded);
		echo $j_decoded."|";
	}
	
	
?>
