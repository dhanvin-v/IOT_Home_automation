<?php
require_once '../includes/DbOperations.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['light1']) and isset($_POST['light2']) and isset($_POST['sensor1']) and isset($_POST['sensor2']) and isset($_POST['motiondetector'])){
		$db = new DbOperations();
		if($db->updateSecondFloor($_POST['light1'], $_POST['light2'], $_POST['sensor1'], $_POST['sensor2'], $_POST['motiondetector'])){
			$response['error']= false;
			$response['message'] = "Update Success";
		}
		else{
				$response['error']= true;
				$response['message']="Update Failed ";
			}
		}
		else {
				$response['error']= true;
				$response['message']="Required Fields missing";
		}
}
else{
	$response['error']= true;
	$response['message']="Invalid Request";
}
echo json_encode($response);
?>
